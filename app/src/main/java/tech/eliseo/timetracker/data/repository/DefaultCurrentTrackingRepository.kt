package tech.eliseo.timetracker.data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.stateIn
import tech.eliseo.timetracker.data.preferences.DataStoreLocalDatasource
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.CurrentTrackingRepository
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultCurrentTrackingRepository @Inject constructor(
    private val localDateTime: DataStoreLocalDatasource
) : CurrentTrackingRepository {

    override val currentTracking: Flow<CurrentTracking> = localDateTime.getCurrentTracking()

    override suspend fun startTracking() {
        localDateTime.setCurrentTracking(CurrentTracking.Started(LocalDateTime.now()))
    }

    override suspend fun stopTracking() {
        localDateTime.setCurrentTracking(CurrentTracking.Stopped)
    }
}