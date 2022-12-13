package tech.eliseo.timetracker.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tech.eliseo.timetracker.data.local.database.dao.CategoryDao
import tech.eliseo.timetracker.data.local.database.entities.CategoryEntity
import tech.eliseo.timetracker.data.local.database.mapper.CategoryEntityMapper
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.repository.CategoryRepository
import javax.inject.Inject

class DefaultCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository, CategoryEntityMapper {
    override val categories: Flow<List<Category>> = categoryDao.getCategories().map {
        it.map { category -> category.toCategory() }
    }

    override suspend fun createCategory(title: String, color: Long, icon: CategoryIcon) {
        categoryDao.insertCategory(CategoryEntity(title = title, color = color, icon = icon.typeName))
    }
}