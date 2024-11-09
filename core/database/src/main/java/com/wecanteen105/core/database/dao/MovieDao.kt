package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.wecanteen105.core.database.model.movie.MovieCastCrossRef
import com.wecanteen105.core.database.model.movie.MovieCrewCrossRef
import com.wecanteen105.core.database.model.movie.MovieEntity
import com.wecanteen105.core.database.model.movie.MovieWithCastEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovieEntities(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieEntity(id:Int) : Flow<MovieEntity>

    @Query("SELECT * FROM movies")
    suspend fun getOneOffMovieEntities(): List<MovieEntity>

    @Query(
        value = """
            SELECT * FROM movies
            WHERE id IN (:ids)
        """
    )
    fun getMovieEntities(ids: Set<Int>): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovies(entities: List<MovieEntity>):List<Long>

    @Upsert
    suspend fun upsertMovies(entities: List<MovieEntity>)

    @Query(
        value = """
            DELETE FROM MOVIES
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteMovies(ids: Set<Int>)



    // NOTE: MovieWithCastEntities. insert crossRef, get a movie of casts in flow and coroutine way.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovieCastCrossRefEntities(movieCastCrossRef: List<MovieCastCrossRef>)

    @Query("DELETE FROM movies_casts WHERE movie_id IN (:movieIds) OR cast_id IN (:castIds)")
    suspend fun deleteMovieCastCrossRefEntities(movieIds: Set<Int> = emptySet(), castIds: Set<Int> = emptySet())

    /**
     * Get the movie associated with a list of cast
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM movies 
            WHERE id = :movieId
        """
    )
    fun getMovieWithCastEntities(movieId: Int): Flow<MovieWithCastEntities>

    @Transaction
    @Query(
        value = """
            SELECT * FROM movies 
            WHERE id = :movieId
        """
    )
    suspend fun getOneOffMovieWithCastEntities(movieId: Int): List<MovieWithCastEntities>

    // NOTE: this fun does NOT delete its associated cast.
    /**
     * Delete a movie and its associated cast
     */
    /*@Transaction
    @Query(
        value = """
            DELETE FROM movies
            WHERE id = :movieId
        """
    )
    suspend fun deleteMovieWithCastEntities(movieId: Int)
*/

    // NOTE: MovieWithCrewEntities. insert crossRef, get a movie of crews in flow and coroutine way.

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreMovieCrewCrossRefEntities(movieCrewCrossRef: List<MovieCrewCrossRef>)

    @Query("DELETE FROM movies_crews WHERE movie_id IN (:movieIds) OR crew_id IN (:crewIds)")
    suspend fun deleteMovieCrewCrossRefEntities(movieIds: Set<Int> = emptySet(), crewIds: Set<Int> = emptySet())

    /**
     * Get the movie associated with a list of crew
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM movies 
            WHERE id = :movieId
        """
    )
    fun getMovieWithCrewEntities(movieId: Int): Flow<MovieWithCastEntities>

    @Transaction
    @Query(
        value = """
            SELECT * FROM movies 
            WHERE id = :movieId
            """
    )
    suspend fun getOneOffMovieWithCrewEntities(movieId: Int): List<MovieWithCastEntities>
}