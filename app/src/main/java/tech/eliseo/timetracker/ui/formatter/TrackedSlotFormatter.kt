package tech.eliseo.timetracker.ui.formatter

import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.text.DateFormat
import java.text.SimpleDateFormat

object TrackedSlotFormatter {

    private val timeZone = TimeZone.of("UTC")

    fun getStartTime(trackedSlot: TrackedSlot): String {
        return trackedSlot.startDate.time.toString()
    }

    fun getEndTime(trackedSlot: TrackedSlot): String {
        return trackedSlot.endDate.time.toString()
    }

    fun getDuration(trackedSlot: TrackedSlot): String {
        return trackedSlot.startDate.toInstant(timeZone)
            .periodUntil(trackedSlot.endDate.toInstant(timeZone), timeZone)
            .toString()
    }
}