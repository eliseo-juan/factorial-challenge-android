package tech.eliseo.timetracker.data.network.mapper

import tech.eliseo.timetracker.data.network.dto.NetworkTrackedSlot
import tech.eliseo.timetracker.domain.model.Category
import tech.eliseo.timetracker.domain.model.TrackedSlot
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface TrackedSlotNetworkMapper {

    private val formatter: DateTimeFormatter
        get() = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun NetworkTrackedSlot.toTrackedSlot(categoryList: List<Category>) = TrackedSlot(
        startDate = formatter.parse(this.startDate, LocalDateTime::from),
        endDate = formatter.parse(this.startDate, LocalDateTime::from),
        category = categoryList.firstOrNull { this.categoryId == it.id },
    )

    fun TrackedSlot.toNetworkTrackedSlot() = NetworkTrackedSlot(
        startDate = this.startDate.format(formatter),
        endDate = this.endDate.format(formatter),
        categoryId = this.category?.id,
    )
}