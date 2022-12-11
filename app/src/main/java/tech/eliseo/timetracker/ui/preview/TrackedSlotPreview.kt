package tech.eliseo.timetracker.ui.preview

import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDate

object FakePreviewData {

    fun getDayListOfTrackedSlot(date: LocalDate): List<TrackedSlot> {
        return (0..(0..4).random()).map {
            val startDate = date.atTime(8 * it + (0..6).random(), (0..59).random())
            TrackedSlot(
                startDate = startDate,
                endDate = startDate.plusMinutes((20..90).random().toLong()),
                category = null
            )
        }
    }

    fun getListOfTrackedSlot(): List<TrackedSlot> {
        val startDate = LocalDate.now()
        return (0..10).map {
            getDayListOfTrackedSlot(startDate.minusDays(it.toLong()))
        }.flatten()
    }

    fun getCategories() : List<Category> {
        return listOf(
            Category("Trabajo", 0xFF00f5d4, CategoryIcon.WORK),
            Category("Deporte", 0xFF00bbf9, CategoryIcon.WORKOUT),
            Category("Entretenimiento", 0xFFf15bb5, CategoryIcon.ENTERTAINMENT),
        )
    }
}