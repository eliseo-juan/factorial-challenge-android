package tech.eliseo.timetracker.domain.usecase

import tech.eliseo.timetracker.domain.model.CurrentTracking

interface OnToggleTrackerUseCase {
    suspend operator fun invoke(currentTracking: CurrentTracking?)
}