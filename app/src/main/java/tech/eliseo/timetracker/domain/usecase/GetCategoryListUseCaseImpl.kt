package tech.eliseo.timetracker.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import javax.inject.Inject

class GetCategoryListUseCaseImpl @Inject constructor() : GetCategoryListUseCase {
    override fun invoke(): Flow<List<Category>> = flow {
        emit(
            listOf(
                Category("Trabajo", 0xFF00f5d4, CategoryIcon.WORK),
                Category("Deporte", 0xFF00bbf9, CategoryIcon.WORKOUT),
                Category("Entretenimiento", 0xFFf15bb5, CategoryIcon.ENTERTAINMENT),
            )
        )
    }
}