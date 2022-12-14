package tech.eliseo.timetracker.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import tech.eliseo.timetracker.data.local.database.mapper.CategoryEntityMapper
import tech.eliseo.timetracker.domain.model.CategoryTest

class CategoryEntityMapperTest : CategoryEntityMapper {

    @Test
    fun testMapper() {
        val category = CategoryTest().getCategory()
        assertEquals(category.toCategoryEntity().toCategory(), category)
    }
}