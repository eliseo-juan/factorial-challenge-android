package tech.eliseo.timetracker.data.local.database.converter

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class LocalDateTimeConverterTest {
    @Test
    fun testConverter() {
        val localDateTime = LocalDateTime.now().minusDays(6)
        val converter = LocalDateTimeConverter()
        val localDateTimeString = converter.fromLocalDateTime(localDateTime)
        assertEquals(converter.toLocalDateTime(localDateTimeString), localDateTime)
    }
}