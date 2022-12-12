package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDate
import javax.inject.Inject

class GetTrackedSlotListByDateUseCaseImpl @Inject constructor(
    private val repository: TrackedSlotRepository
) : GetTodayTrackedSlotListUseCase {

    override fun invoke(date: LocalDate): Flow<List<TrackedSlot>> = repository.getTrackedSlotsByDate(date)
}