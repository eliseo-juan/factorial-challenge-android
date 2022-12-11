package tech.eliseo.timetracker.domain.usecase

import android.util.Log
import kotlinx.coroutines.flow.*
import tech.eliseo.timetracker.domain.model.CurrentTracking
import tech.eliseo.timetracker.domain.model.TrackedSlot
import tech.eliseo.timetracker.domain.repository.CurrentTrackingRepository
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDateTime
import javax.inject.Inject

class   OnToggleTrackerUseCaseImpl @Inject constructor(
    private val trackedSlotRepository: TrackedSlotRepository,
    private val currentTrackingRepository: CurrentTrackingRepository
) : OnToggleTrackerUseCase {
    override suspend fun invoke() {
        when (val state = currentTrackingRepository.currentTracking.last()) {
            is CurrentTracking.Started -> {
                currentTrackingRepository.stopTracking()
                trackedSlotRepository.add(
                    TrackedSlot(
                        startDate = state.startDate,
                        endDate = LocalDateTime.now(),
                        category = null
                    )
                )
            }
            is CurrentTracking.Stopped -> currentTrackingRepository.startTracking()
        }
    }
}