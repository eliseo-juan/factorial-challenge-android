package tech.eliseo.timetracker.data.local.preferences

import java.time.LocalDateTime

data class UserPreferences(
    val currentTracking: LocalDateTime? = null
)
