package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface TrackedSlotRepository {
    val trackedSlots: Flow<List<TrackedSlot>>

    val lastTrackedSlot: Flow<TrackedSlot>

    suspend fun add(trackedSlot: TrackedSlot)
}