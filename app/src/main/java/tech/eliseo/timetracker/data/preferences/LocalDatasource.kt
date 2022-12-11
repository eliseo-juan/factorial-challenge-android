package tech.eliseo.timetracker.data.preferences

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LocalDatasource {
    fun getCurrentTracking(): Flow<LocalDate>

    suspend fun setCurrentTracking(date: LocalDate)
}