package tech.eliseo.timetracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon

interface CategoryRepository {

    val categories: Flow<List<Category>>

    suspend fun createCategory(title: String, color: Long, icon: CategoryIcon)
}