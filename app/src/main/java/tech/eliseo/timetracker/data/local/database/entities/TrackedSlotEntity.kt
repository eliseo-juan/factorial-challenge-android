package tech.eliseo.timetracker.data.local.database.entities

import androidx.room.Entity
import java.time.LocalDateTime

@Entity(primaryKeys = ["startDate", "endDate"])
data class TrackedSlotEntity(
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var categoryId: Long?,
)
