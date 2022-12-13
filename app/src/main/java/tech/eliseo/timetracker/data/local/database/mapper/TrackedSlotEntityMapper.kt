package tech.eliseo.timetracker.data.local.database.mapper

import tech.eliseo.timetracker.data.local.database.entities.TrackedSlotEntity
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface TrackedSlotEntityMapper {

    fun TrackedSlotEntity.toTrackedSlot(categoryList: List<Category>) = TrackedSlot(
        startDate = this.startDate,
        endDate = this.endDate,
        category = categoryList.firstOrNull { this.categoryId == it.id },
    )

    fun TrackedSlot.toTrackedSlotEntity() = TrackedSlotEntity(
        startDate = this.startDate,
        endDate = this.endDate,
        categoryId = this.category?.id,
    )
}