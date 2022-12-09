package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface GetTrackedSlotListUseCase {

    operator fun invoke() : Flow<List<TrackedSlot>>
}