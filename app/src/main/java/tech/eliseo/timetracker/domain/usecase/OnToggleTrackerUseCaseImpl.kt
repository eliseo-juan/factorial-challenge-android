package tech.eliseo.timetracker.domain.usecase

import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.CurrentTrackingRepository
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDateTime
import javax.inject.Inject

class OnToggleTrackerUseCaseImpl @Inject constructor(
    private val trackedSlotRepository: TrackedSlotRepository,
    private val currentTrackingRepository: CurrentTrackingRepository
) : OnToggleTrackerUseCase {
    override suspend fun invoke(currentTracking: CurrentTracking?) {
        when (currentTracking) {
            is CurrentTracking.Started -> {
                currentTrackingRepository.stopTracking()
                trackedSlotRepository.add(
                    TrackedSlot(
                        startDate = currentTracking.startDate,
                        endDate = LocalDateTime.now(),
                        category = null
                    )
                )
            }
            is CurrentTracking.Stopped -> currentTrackingRepository.startTracking()
            null -> currentTrackingRepository.startTracking()
        }
    }
}