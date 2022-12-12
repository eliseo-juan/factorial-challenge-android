package tech.eliseo.timetracker.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryDB(
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0,
    val title: String,
    val icon: String,
    val color: Long,
)