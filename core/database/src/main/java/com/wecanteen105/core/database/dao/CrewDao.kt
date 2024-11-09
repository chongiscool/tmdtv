package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.people.CrewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CrewDao {
    @Query("SELECT * FROM crews")
    fun getCrewEntities(): Flow<List<CrewEntity>>

    @Query("SELECT * FROM crews")
    suspend fun getOneOffCrewEntities(): List<CrewEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreCrews(entities: List<CrewEntity>): List<Long>

    @Upsert
    suspend fun upsertCrews(entities: List<CrewEntity>)

    /**
     * Deletes rows in the db matching the specified [ids].
     * Typically use to fetch [ids] from CrossRef entity, then to delete rows.
     */
    @Query("DELETE FROM crews WHERE id IN (:ids)")
    suspend fun deleteCrews(ids: Set<Int>)
}