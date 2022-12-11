package tech.eliseo.timetracker.ui.formatter

import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object TrackedSlotFormatter {

    fun getStartTime(trackedSlot: TrackedSlot): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(trackedSlot.startDate)
    }

    fun getEndTime(trackedSlot: TrackedSlot): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(trackedSlot.endDate)
    }

    fun getDuration(trackedSlot: TrackedSlot): String {
        return ""
    }
}