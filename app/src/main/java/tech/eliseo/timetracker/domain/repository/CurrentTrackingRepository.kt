package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tech.eliseo.timetracker.domain.model.CurrentTracking

interface CurrentTrackingRepository {
    val currentTracking: Flow<CurrentTracking>

    suspend fun stopTracking()
    suspend fun startTracking()
}