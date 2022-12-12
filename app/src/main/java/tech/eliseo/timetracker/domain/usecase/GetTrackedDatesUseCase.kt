package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetTrackedDatesUseCase {
    operator fun invoke() : Flow<List<LocalDate>>
}