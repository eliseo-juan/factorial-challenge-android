package tech.eliseo.timetracker.domain.model

import kotlinx.datetime.LocalDateTime


data class TrackedSlot(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val category: String?
)
