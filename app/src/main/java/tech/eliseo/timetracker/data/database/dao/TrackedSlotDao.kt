package tech.eliseo.timetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.data.database.dto.TrackedSlotDB

@Dao
interface TrackedSlotDao {
    @Query("SELECT * FROM trackedslotdb")
    fun getTrackedSlots(): Flow<List<TrackedSlotDB>>

    @Insert
    suspend fun insertTrackedSlot(item: TrackedSlotDB)

    @Insert
    suspend fun insertTrackedSlotList(items: List<TrackedSlotDB>)

    @Update
    suspend fun updateTrackedSlot(item: TrackedSlotDB)

    @Query("SELECT * FROM trackedslotdb WHERE date(startDate)=date('now') ORDER BY datetime(startDate) ASC")
    fun getTrackedSlotsByDate(): Flow<List<TrackedSlotDB>>

    @Query("SELECT * FROM trackedslotdb WHERE date(startDate)=date('now') ORDER BY datetime(startDate) DESC")
    fun getTodayTrackedSlots(): Flow<List<TrackedSlotDB>>
}
