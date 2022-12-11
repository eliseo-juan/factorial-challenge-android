package tech.eliseo.timetracker.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class TrackedSlotDB(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var category: String?
)
