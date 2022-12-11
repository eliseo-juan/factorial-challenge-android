package tech.eliseo.timetracker.domain.model

import java.time.LocalDateTime

data class TrackedSlot(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val category: String?
)
