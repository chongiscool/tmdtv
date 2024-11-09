package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wecanteen105.core.database.model.movie.MovieFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieFtsEntity>)

    @Query("SELECT rowid FROM moviesFts WHERE moviesFts MATCH :query")
    fun searchAllMovieIds(query:String): Flow<List<Int>>

    @Query("SELECT COUNT(*) FROM moviesFts")
    fun getCount(): Flow<Int>
}