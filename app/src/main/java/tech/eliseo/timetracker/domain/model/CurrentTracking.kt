package tech.eliseo.timetracker.domain.model

import java.time.LocalDateTime

sealed class CurrentTracking {
    data class Started(val startDate: LocalDateTime) : CurrentTracking()
    object Stopped : CurrentTracking()
}
