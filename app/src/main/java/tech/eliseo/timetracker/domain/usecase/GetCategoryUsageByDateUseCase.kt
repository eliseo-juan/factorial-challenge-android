package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category
import java.time.LocalDate

interface GetCategoryUsageByDateUseCase {
    suspend operator fun invoke(date: LocalDate) : Flow<List<Pair<Category?, Int>>>
}