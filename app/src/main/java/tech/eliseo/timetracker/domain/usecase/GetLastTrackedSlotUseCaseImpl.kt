package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import javax.inject.Inject

class GetLastTrackedSlotUseCaseImpl @Inject constructor(
    private val repository: TrackedSlotRepository
) : GetLastTrackedSlotUseCase {

    override fun invoke(): Flow<TrackedSlot> = repository.lastTrackedSlot
}