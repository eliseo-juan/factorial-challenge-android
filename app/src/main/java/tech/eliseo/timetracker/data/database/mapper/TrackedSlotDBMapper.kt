package tech.eliseo.timetracker.data.database.mapper

import tech.eliseo.timetracker.data.database.dto.TrackedSlotDB
import tech.eliseo.timetracker.domain.model.TrackedSlot

interface TrackedSlotDBMapper {

    fun TrackedSlotDB.toTrackedSlot() = TrackedSlot(
        startDate = this.startDate,
        endDate = this.endDate,
        category = this.category,
    )

    fun TrackedSlot.toTrackedSlotDB() = TrackedSlotDB(
        startDate = this.startDate,
        endDate = this.endDate,
        category = this.category,
    )
}