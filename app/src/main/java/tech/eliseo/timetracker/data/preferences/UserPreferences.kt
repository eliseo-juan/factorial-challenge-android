package tech.eliseo.timetracker.data.preferences

import java.time.LocalDateTime

data class UserPreferences(
    val currentTracking: LocalDateTime? = null
)
