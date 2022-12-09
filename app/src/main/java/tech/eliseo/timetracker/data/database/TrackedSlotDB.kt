package tech.eliseo.timetracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity
data class TrackedSlotDB(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var startDate: LocalDateTime,
    var endDate: LocalDateTime,
    var category: String?
)
