package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.SeasonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeasonDao {

    @Query("SELECT * FROM seasons")
    suspend fun getOneOffSeasonEntities():List<SeasonEntity>

    @Query("SELECT * FROM seasons WHERE tvDetailId = :tvDetailId")
    fun getSeasonEntities(tvDetailId: Int): Flow<List<SeasonEntity>>

    @Query("SELECT * FROM seasons WHERE tvDetailId = :tvDetailId")
    suspend fun getOneOffSeasonEntities(tvDetailId: Int): List<SeasonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreSeasons(entities: List<SeasonEntity>): List<Long>

    @Upsert
    suspend fun upsertSeasons(entities: List<SeasonEntity>)

    @Query("DELETE FROM seasons WHERE tvDetailId IN (:tvDetailIds)")
    suspend fun deleteSeasons(tvDetailIds: Set<Int>)

    @Query("DELETE FROM seasons WHERE id IN (:ids)")
    suspend fun deleteSeasonByIds(ids:Set<Int>)
}