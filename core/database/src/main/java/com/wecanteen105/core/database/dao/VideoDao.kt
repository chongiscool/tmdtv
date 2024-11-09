package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM videos")
    suspend fun getOneOffVideoEntities(): List<VideoEntity>

    @Query("SELECT * FROM videos WHERE movieTvDetailId = :movieTvDetailId")
    fun getVideoEntities(movieTvDetailId: Int): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE movieTvDetailId = :movieTvDetailId")
    suspend fun getOneOffVideoEntities(movieTvDetailId: Int): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreVideos(entities: List<VideoEntity>): List<Long>

    @Upsert
    suspend fun upsertVideos(entities: List<VideoEntity>)

    @Query("DELETE FROM videos WHERE movieTvDetailId IN (:movieTvDetailIds)")
    suspend fun deleteVideos(movieTvDetailIds: Set<Int>)

    @Query("DELETE FROM videos WHERE id IN (:ids)")
    suspend fun deleteVideoByIds(ids:Set<String>)

}