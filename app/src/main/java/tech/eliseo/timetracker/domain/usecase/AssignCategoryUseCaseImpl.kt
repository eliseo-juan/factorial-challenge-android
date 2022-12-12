package tech.eliseo.timetracker.domain.usecase

import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import javax.inject.Inject

class AssignCategoryUseCaseImpl @Inject constructor(
    val repository: TrackedSlotRepository
) : AssignCategoryUseCase {
    override suspend fun invoke(trackedSlot: TrackedSlot, category: Category) {
        repository.assignCategory(trackedSlot, category)
    }
}