package tech.eliseo.timetracker.domain.usecase

import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface AssignCategoryUseCase {
    suspend operator fun invoke(trackedSlot: TrackedSlot, category: Category)
}