package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDate

interface TrackedSlotRepository {
    val trackedSlots: Flow<List<TrackedSlot>>

    fun getTrackedSlotsByDate(date: LocalDate) : Flow<List<TrackedSlot>>

    suspend fun add(trackedSlot: TrackedSlot)
}