package tech.eliseo.timetracker.data.network.dto

data class NetworkTrackedSlot(
    var startDate: String,
    var endDate: String,
    var categoryId: Long?,
)

fun NetworkTrackedSlot.toHashMap(): Map<String, Any> = hashMapOf<String, Any>(
    "startDate" to this.startDate,
    "endDate" to this.endDate,
).also { map ->
    this.categoryId?.let { map.toMutableMap().put("categoryId", it) }

}