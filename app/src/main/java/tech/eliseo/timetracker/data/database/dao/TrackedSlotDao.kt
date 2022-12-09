package tech.eliseo.timetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.data.database.TrackedSlotDB

@Dao
interface TrackedSlotDao {
    @Query("SELECT * FROM trackedslotdb ORDER BY uid DESC LIMIT 10")
    fun getTrackedSlots(): Flow<List<TrackedSlotDB>>

    @Insert
    suspend fun insertTrackedSlot(item: TrackedSlotDB)

    @Query("SELECT * FROM trackedslotdb ORDER BY endDate DESC LIMIT 1")
    fun getLastTrackedSlot(): Flow<TrackedSlotDB>
}
