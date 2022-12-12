package tech.eliseo.timetracker.ui.formatter


import org.junit.Assert
import org.junit.Test
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDateTime


class TrackedSlotViewModelTest {

    @Test
    fun getDuration() {
        val dateTime = LocalDateTime.now()
        val trackedSlot = TrackedSlot(
            startDate = dateTime.minusHours(2).minusMinutes(40).minusSeconds(20),
            endDate = dateTime,
        )
        Assert.assertEquals(TrackedSlotFormatter.getDuration(trackedSlot), "2h 40m 20s")
    }
}
