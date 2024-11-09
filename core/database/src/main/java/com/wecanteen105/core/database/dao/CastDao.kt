package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.people.CastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CastDao {

    @Query("SELECT * FROM casts")
    fun getCastEntities(): Flow<List<CastEntity>>

    @Query("SELECT * FROM casts")
    suspend fun getOneOffCastEntities(): List<CastEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreCasts(entities: List<CastEntity>): List<Long>

    @Upsert
    suspend fun upsertCasts(entities: List<CastEntity>)

    /**
     * Deletes rows in the db matching the specified [ids].
     * Typically use to fetch [ids] from CrossRef entity, then to delete rows.
     */
    @Query(
        value = """
            DELETE FROM casts
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteCasts(ids: Set<Int>)
}