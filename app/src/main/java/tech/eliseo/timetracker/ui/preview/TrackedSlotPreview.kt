package tech.eliseo.timetracker.ui.preview

import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.CategoryIcon
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDate

object FakePreviewData {

    fun getDayListOfTrackedSlot(date: LocalDate): List<TrackedSlot> {
        val categories = getCategories()
        return (0..(3..10).random()).map {
            val startDate = date.atTime((0..23).random(), (0..59).random())
            TrackedSlot(
                startDate = startDate,
                endDate = startDate.plusMinutes((20..90).random().toLong()),
                category = categories.random()
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
            Category(id = 1, title ="Trabajo", color = 0xFF00f5d4, icon = CategoryIcon.WORK),
            Category(id = 2, title ="Deporte", color = 0xFF00bbf9, icon = CategoryIcon.WORKOUT),
            Category(id = 3, title ="Entretenimiento", color = 0xFFf15bb5, icon = CategoryIcon.ENTERTAINMENT),
        )
    }
}