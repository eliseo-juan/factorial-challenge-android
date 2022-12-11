package tech.eliseo.timetracker.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.domain.model.CurrentTracking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreLocalDatasource @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val currentTrackingKey = stringPreferencesKey("CURRENT_TRACKING")

    suspend fun setCurrentTracking(currentTracking: CurrentTracking) {
        context.dataStore.edit { preferences ->
            (currentTracking as? CurrentTracking.Started)
                ?.startDate
                ?.format(formatter)?.let {
                    preferences[currentTrackingKey] = it
                } ?: preferences.remove(currentTrackingKey)
        }
    }

    fun getCurrentTracking(): Flow<CurrentTracking> {
        return context.dataStore.data
            .catch {
                emit(emptyPreferences())
            }.map {
                it[currentTrackingKey]
            }.map {
                it?.let {
                    formatter.parse(it, LocalDateTime::from)
                }
            }.map {
                it?.let { CurrentTracking.Started(it) } ?: CurrentTracking.Stopped
            }
    }
}