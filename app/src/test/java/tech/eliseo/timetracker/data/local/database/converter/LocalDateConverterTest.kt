package tech.eliseo.timetracker.data.local.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class LocalDateConverterTest {

    @Test
    fun testConverter() {
        val localDate = LocalDate.now().minusDays(6)
        val converter = LocalDateConverter()
        val localDateString = converter.fromLocalDate(localDate)
        assertEquals(converter.toLocalDate(localDateString), localDate)
    }
}