package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.wecanteen105.core.database.model.movie.MovieDetailEntity
import com.wecanteen105.core.database.model.movie.MovieDetailWithReviews
import com.wecanteen105.core.database.model.movie.MovieDetailWithVideos
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {
    // MovieDetail: select, upsert, insert, delete
    @Query("SELECT * FROM movie_details")
    fun getMovieDetailEntities(): Flow<List<MovieDetailEntity>>

    @Query(
        value = """
            SELECT * FROM movie_details
            WHERE id IN (:ids)
        """
    )
    fun getMovieDetailEntities(ids: Set<Int>): Flow<List<MovieDetailEntity>>

    @Query("SELECT * FROM movie_details WHERE id = :id")
    fun getMovieDetailEntity(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM movie_details")
    suspend fun getOneOffMovieDetailEntities(): List<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovieDetails(entities: List<MovieDetailEntity>): List<Long>

    @Upsert
    suspend fun upsertMovieDetails(entities: List<MovieDetailEntity>)

    @Query(
        value = """
            DELETE FROM movie_details
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteMovieDetails(ids: Set<Int>)

    /**
     * Reviews, for insert, upsert, delete to check ReviewDao.
     */
    @Transaction
    @Query("SELECT * FROM movie_details")
    fun getMovieDetailWithReviews(): Flow<List<MovieDetailWithReviews>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM movie_details
            WHERE id = :movieId
        """
    )
    fun getMovieDetailWithReviewsById(movieId:Int):Flow<MovieDetailWithReviews>

    @Transaction
    @Query(
        value = """
            SELECT * FROM movie_details
            WHERE id = :movieId
        """
    )
    suspend fun getOneOffMovieDetailWithReviewsById(movieId:Int):List<MovieDetailWithReviews>

    // Videos
    @Transaction
    @Query("SELECT * FROM movie_details")
    fun getMovieDetailWithVideos():Flow<List<MovieDetailWithVideos>>

    @Transaction
    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    fun getMovieDetailWithVideosById(movieId:Int):Flow<MovieDetailWithVideos>

    @Transaction
    @Query("SELECT * FROM movie_details WHERE id IN (:movieIds)")
    suspend fun getOneOffMovieDetailWithVideosById(movieIds:List<Int>):List<MovieDetailWithVideos>

}