package tech.eliseo.timetracker.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tech.eliseo.timetracker.data.local.database.entities.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categoryentity ")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Insert
    suspend fun insertCategory(item: CategoryEntity)

    @Insert
    suspend fun insertCategoryList(items: List<CategoryEntity>)

    @Query("SELECT * FROM categoryentity WHERE categoryId = :categoryId")
    fun getCategoryById(categoryId: Long): Flow<List<CategoryEntity>>
}
