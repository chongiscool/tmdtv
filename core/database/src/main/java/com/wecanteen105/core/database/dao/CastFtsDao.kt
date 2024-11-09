package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wecanteen105.core.database.model.people.CastFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CastFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(casts: List<CastFtsEntity>)

    @Query("SELECT rowid from castsFts WHERE castsFts MATCH :query")
    fun searchAllCastIds(query: String): Flow<List<Int>>

    @Query("SELECT count(*) FROM castsFts")
    fun getCount(): Flow<Int>
}