package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.wecanteen105.core.database.model.tv.TvDetailEntity
import com.wecanteen105.core.database.model.tv.TvDetailWithReviews
import com.wecanteen105.core.database.model.tv.TvDetailWithSeasons
import com.wecanteen105.core.database.model.tv.TvDetailWithVideos
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDetailDao {

    // TvDetail: select, upsert, insert, delete
    @Query("SELECT * FROM tv_details")
    fun getTvDetailEntities(): Flow<List<TvDetailEntity>>

    @Query(
        value = """
            SELECT * FROM tv_details
            WHERE id IN (:ids)
        """
    )
    fun getTvDetailEntities(ids: Set<Int>): Flow<List<TvDetailEntity>>

    @Query("SELECT * FROM tv_details WHERE id = :id")
    fun getTvDetailEntity(id: Int): Flow<TvDetailEntity>

    @Query("SELECT * FROM tv_details")
    suspend fun getOneOffTvDetailEntities(): List<TvDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTvDetails(entities: List<TvDetailEntity>): List<Long>

    @Upsert
    suspend fun upsertTvDetails(entities: List<TvDetailEntity>)

    @Query(
        value = """
            DELETE FROM tv_details
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteTvDetails(ids: Set<Int>)


    // Seasons
    @Transaction
    @Query("SELECT * FROM tv_details")
    fun getTvDetailWithSeasons(): Flow<List<TvDetailWithSeasons>>

    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    fun getTvDetailWithSeasonsById(tvDetailId:Int):Flow<TvDetailWithSeasons>

    /**
     * Why return list?
     * If result is Empty, return empty list, not null.
     */
    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    suspend fun getOneOffTvDetailWithSeasonsById(tvDetailId:Int):List<TvDetailWithSeasons>

    /**
     * Reviews, for insert, upsert, delete to check ReviewDao.
     * Same as Seasons, Videos
     */
    @Transaction
    @Query("SELECT * FROM tv_details")
    fun getTvDetailWithReviews(): Flow<List<TvDetailWithReviews>>

    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    fun getTvDetailWithReviewsById(tvDetailId:Int):Flow<TvDetailWithReviews>

    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    suspend fun getOneOffTvDetailWithReviewsById(tvDetailId:Int):List<TvDetailWithReviews>

    // Videos
    @Transaction
    @Query("SELECT * FROM tv_details")
    fun getTvDetailWithVideos(): Flow<List<TvDetailWithVideos>>

    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    fun getTvDetailWithVideosById(tvDetailId:Int):Flow<TvDetailWithVideos>

    @Transaction
    @Query("SELECT * FROM tv_details WHERE id = :tvDetailId")
    suspend fun getOneOffTvDetailWithVideosById(tvDetailId:Int):List<TvDetailWithVideos>

}