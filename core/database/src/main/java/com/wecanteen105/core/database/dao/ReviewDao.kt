package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Query("SELECT * FROM reviews")
    suspend fun getOneOffReviewEntities(): List<ReviewEntity>

    @Query("SELECT * FROM reviews WHERE movieTvDetailId = :movieTvDetailId")
    fun getReviewEntities(movieTvDetailId:Int): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM reviews WHERE movieTvDetailId = :movieTvDetailId")
    suspend fun getOneOffReviewEntities(movieTvDetailId: Int): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreReviews(entities: List<ReviewEntity>): List<Long>

    @Upsert
    suspend fun upsertReviews(entities: List<ReviewEntity>)

    @Query(value = "DELETE FROM reviews WHERE movieTvDetailId IN (:movieTvDetailIds)")
    suspend fun deleteReviews(movieTvDetailIds: Set<Int>)

    @Query("DELETE FROM reviews WHERE id IN (:ids)")
    suspend fun deleteReviewByIds(ids:Set<String>)
}