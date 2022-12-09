package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface GetLastTrackedSlotUseCase {

    operator fun invoke() : Flow<TrackedSlot>
}