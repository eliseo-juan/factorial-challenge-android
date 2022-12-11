package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category

interface GetCategoryListUseCase {
    operator fun invoke() : Flow<List<Category>>
}