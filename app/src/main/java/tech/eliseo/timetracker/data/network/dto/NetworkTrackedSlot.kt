package tech.eliseo.timetracker.data.network.dto

data class NetworkTrackedSlot(
    var startDate: String,
    var endDate: String,
    var categoryId: Long?,
)