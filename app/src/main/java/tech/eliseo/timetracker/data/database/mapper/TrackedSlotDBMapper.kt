package tech.eliseo.timetracker.data.database.mapper

import tech.eliseo.timetracker.data.database.dto.TrackedSlotDB
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface TrackedSlotDBMapper {

    fun TrackedSlotDB.toTrackedSlot(categoryList: List<Category>) = TrackedSlot(
        startDate = this.startDate,
        endDate = this.endDate,
        category = categoryList.firstOrNull { this.categoryId == it.id },
    )

    fun TrackedSlot.toTrackedSlotDB() = TrackedSlotDB(
        startDate = this.startDate,
        endDate = this.endDate,
        categoryId = this.category?.id,
    )
}