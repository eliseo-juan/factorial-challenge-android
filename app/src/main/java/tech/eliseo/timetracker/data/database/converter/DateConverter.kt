package tech.eliseo.timetracker.data.database.converter

import androidx.room.TypeConverter
import kotlinx.datetime.*

class DateConverter {

    private val timeZone = TimeZone.of("UTC")

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { Instant.fromEpochSeconds(it).toLocalDateTime(timeZone) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toInstant(timeZone)?.toEpochMilliseconds()

    }
}