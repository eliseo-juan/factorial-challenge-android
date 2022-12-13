package tech.eliseo.timetracker.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0,
    val title: String,
    val icon: String,
    val color: Long,
)