package tech.eliseo.timetracker.ui.formatter

import android.content.Context
import tech.eliseo.timetracker.R
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

object TrackedSlotFormatter {

    private const val MINUTE_LENGTH = 60
    private const val HOUR_LENGTH = 60 * MINUTE_LENGTH

    fun getStartTime(trackedSlot: TrackedSlot): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(trackedSlot.startDate)
    }

    fun getEndTime(trackedSlot: TrackedSlot): String {
        return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(trackedSlot.endDate)
    }

    fun getTimePeriod(trackedSlot: TrackedSlot): String {
        return "${getStartTime(trackedSlot)} - ${getEndTime(trackedSlot)}"
    }

    fun getDuration(context: Context, trackedSlot: TrackedSlot): String {
        var seconds: Int =
            ChronoUnit.SECONDS.between(trackedSlot.startDate, trackedSlot.endDate).toInt()
        val hours = seconds / HOUR_LENGTH
        seconds -= hours * HOUR_LENGTH
        val minutes = seconds / MINUTE_LENGTH
        seconds -= minutes * MINUTE_LENGTH
        return when (hours) {
            0 -> context.getString(R.string.tracked_slot_duration_m_s, minutes, seconds)
            else -> context.getString(R.string.tracked_slot_duration_h_m, hours, minutes)
        }
    }
}