package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.CurrentTracking
import java.time.LocalDateTime

interface GetCurrentTrackingUseCase {
    operator fun invoke() : Flow<CurrentTracking>
}