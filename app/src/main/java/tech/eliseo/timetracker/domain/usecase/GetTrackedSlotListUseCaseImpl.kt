package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import javax.inject.Inject

class GetTrackedSlotListUseCaseImpl @Inject constructor(
    private val repository: TrackedSlotRepository
) : GetTrackedSlotListUseCase {
    override fun invoke(): Flow<List<TrackedSlot>> = repository.trackedSlots
}