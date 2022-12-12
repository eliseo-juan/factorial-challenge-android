package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDate

interface TrackedSlotRepository {
    val trackedSlots: Flow<List<TrackedSlot>>

    fun getTrackedSlotsByDate(date: LocalDate): Flow<List<TrackedSlot>>
    fun getTodayTrackedSlots(): Flow<List<TrackedSlot>>
    fun getTrackedDates(): Flow<List<LocalDate>>
    suspend fun assignCategory(trackedSlot: TrackedSlot, category: Category)
    suspend fun add(trackedSlot: TrackedSlot)

    suspend fun populate()
}