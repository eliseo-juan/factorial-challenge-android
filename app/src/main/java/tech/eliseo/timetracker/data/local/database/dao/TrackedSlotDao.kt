package tech.eliseo.timetracker.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.data.local.database.entities.TrackedSlotEntity
import java.time.LocalDate

@Dao
interface TrackedSlotDao {
    @Query("SELECT * FROM trackedslotentity")
    fun getTrackedSlots(): Flow<List<TrackedSlotEntity>>

    @Insert
    suspend fun insertTrackedSlot(item: TrackedSlotEntity)

    @Insert
    suspend fun insertTrackedSlotList(items: List<TrackedSlotEntity>)

    @Update
    suspend fun updateTrackedSlot(item: TrackedSlotEntity)

    @Query("SELECT * FROM trackedslotentity WHERE date(startDate)=date(:localDate) ORDER BY datetime(startDate) ASC")
    fun getTrackedSlotsByDate(localDate: LocalDate): Flow<List<TrackedSlotEntity>>

    @Query("SELECT * FROM trackedslotentity WHERE date(startDate)=date('now') ORDER BY datetime(startDate) DESC")
    fun getTodayTrackedSlots(): Flow<List<TrackedSlotEntity>>

    @Query("SELECT DISTINCT date(startDate) FROM trackedslotentity ORDER BY date(startDate) ASC")
    fun getTrackedDates(): Flow<List<LocalDate>>
}
