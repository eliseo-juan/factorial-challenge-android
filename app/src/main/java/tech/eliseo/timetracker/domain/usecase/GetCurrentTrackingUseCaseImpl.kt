package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.repository.CurrentTrackingRepository
import javax.inject.Inject

class GetCurrentTrackingUseCaseImpl @Inject constructor(
    private val repository: CurrentTrackingRepository
) : GetCurrentTrackingUseCase {
    override fun invoke(): Flow<CurrentTracking> =
        repository.currentTracking
}