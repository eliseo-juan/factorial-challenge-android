package tech.eliseo.timetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.data.database.dto.CategoryDB
import tech.eliseo.timetracker.data.database.dto.TrackedSlotDB
import tech.eliseo.timetracker.domain.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categorydb ")
    fun getCategories(): Flow<List<CategoryDB>>

    @Insert
    suspend fun insertCategory(item: CategoryDB)

    @Query("SELECT * FROM categorydb WHERE categoryId = :categoryId")
    fun getCategoryById(categoryId: Long): Flow<List<CategoryDB>>
}
