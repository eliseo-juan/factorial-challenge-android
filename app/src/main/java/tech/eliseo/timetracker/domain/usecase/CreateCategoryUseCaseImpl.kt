package tech.eliseo.timetracker.domain.usecase

import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.repository.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCaseImpl @Inject constructor(
    private val repository: CategoryRepository
) : CreateCategoryUseCase {
    override suspend fun invoke(name: String) {
        repository.createCategory(title = name, color = 0xFFfee440, icon = CategoryIcon.DEFAULT)
    }
}