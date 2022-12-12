package tech.eliseo.timetracker.data.database.dto

import androidx.room.Entity
import java.time.LocalDateTime

@Entity(primaryKeys = ["startDate", "endDate"])
data class TrackedSlotDB(
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var categoryId: Long?,
)
