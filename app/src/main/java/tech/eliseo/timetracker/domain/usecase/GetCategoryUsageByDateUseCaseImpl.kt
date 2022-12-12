package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class GetCategoryUsageByDateUseCaseImpl @Inject constructor(
    private val trackedSlotRepository: TrackedSlotRepository
) : GetCategoryUsageByDateUseCase {
    override suspend fun invoke(date: LocalDate): Flow<List<Pair<Category?, Int>>> {
        return trackedSlotRepository.getTrackedSlotsByDate(date).map { trackedSlotList ->
            trackedSlotList
                .map { Pair(it.category, getSecondsBetweenStartAndEnd(it)) }
                .groupBy { it.first }
                .map {
                    Pair(it.key, it.value.sumOf { seconds -> seconds.second })
                }.sortedByDescending {
                    it.second
                }
        }
    }

    private fun getSecondsBetweenStartAndEnd(trackedSlot: TrackedSlot) =
        ChronoUnit.SECONDS.between(trackedSlot.startDate, trackedSlot.endDate).toInt()
}