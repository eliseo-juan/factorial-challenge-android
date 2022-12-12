package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoryListUseCaseImpl @Inject constructor(
    private val repository: CategoryRepository
) : GetCategoryListUseCase {
    override fun invoke(): Flow<List<Category>> = repository.categories
}