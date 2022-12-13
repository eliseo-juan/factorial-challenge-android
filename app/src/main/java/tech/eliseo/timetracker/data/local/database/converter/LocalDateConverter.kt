package tech.eliseo.timetracker.data.local.database.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            return formatter.parse(value, LocalDate::from)
        }
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }
}