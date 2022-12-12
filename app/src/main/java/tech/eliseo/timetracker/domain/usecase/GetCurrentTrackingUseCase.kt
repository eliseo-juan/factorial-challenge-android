package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.CurrentTracking

interface GetCurrentTrackingUseCase {
    operator fun invoke() : Flow<CurrentTracking>
}