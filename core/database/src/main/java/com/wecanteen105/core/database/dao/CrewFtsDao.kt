package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wecanteen105.core.database.model.people.CrewFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CrewFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(crews: List<CrewFtsEntity>)

    @Query("SELECT rowid FROM crewsFts WHERE crewsFts MATCH :query")
    fun searchAllCrewIds(query: String): Flow<List<Int>>

    @Query("SELECT COUNT(*) FROM crewsFts")
    fun getCount(): Flow<Int>
}