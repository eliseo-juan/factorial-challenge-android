package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.domain.repository.TrackedSlotRepository
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class GetTrackedDatesUseCaseImpl @Inject constructor(
    val repository: TrackedSlotRepository
) : GetTrackedDatesUseCase {
    override fun invoke(): Flow<List<LocalDate>> =
        repository.getTrackedDates().map { list ->
            if (list.isEmpty()) return@map listOf(LocalDate.now())
            return@map list.firstOrNull().let { localDate ->
                localDate?.let { ChronoUnit.DAYS.between(localDate, LocalDate.now()).toInt() }
                    ?: 1
            }.let { numberOfDays ->
                (numberOfDays downTo 0).map {
                    LocalDate.now().minusDays(it.toLong())
                }
            }
        }
}