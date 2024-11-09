package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wecanteen105.core.database.model.tv.TvFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvs: List<TvFtsEntity>)

    @Query("SELECT rowid FROM tvsFts WHERE tvsFts MATCH :query")
    fun searchAllTvIds(query: String): Flow<List<Int>>

    @Query("SELECT COUNT(*) FROM tvsFts")
    fun getCount():Flow<Int>
}