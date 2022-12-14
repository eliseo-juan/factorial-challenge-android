package tech.eliseo.timetracker.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import tech.eliseo.timetracker.data.local.database.dao.CategoryDao
import tech.eliseo.timetracker.data.local.database.mapper.CategoryEntityMapper
import tech.eliseo.timetracker.domain.model.CategoryTest

class DefaultCategoryRepositoryTest : CategoryEntityMapper {


    @Test
    fun getCategories() = runTest {
        val categoryDao = mock(CategoryDao::class.java)
        val categories = CategoryTest().getCategories()
        `when`(categoryDao.getCategories()).thenReturn(flow { emit(categories.map { it.toCategoryEntity() }) })
        val repository = DefaultCategoryRepository(categoryDao)
        assertEquals(repository.categories.first(), categories)
    }

    @Test
    fun createCategory() = runTest {
        val categoryDao = mock(CategoryDao::class.java)
        val repository = DefaultCategoryRepository(categoryDao)
        val category = CategoryTest().getCategory()
        `when`(categoryDao.insertCategory(category.toCategoryEntity())).thenReturn(Unit)
        repository.createCategory(category.title, category.color, category.icon)
        verify(categoryDao, times(1)).insertCategory(category.toCategoryEntity())
    }
}