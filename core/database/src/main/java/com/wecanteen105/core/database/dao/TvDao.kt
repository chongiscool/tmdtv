package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.wecanteen105.core.database.model.tv.TvCastCrossRef
import com.wecanteen105.core.database.model.tv.TvCrewCrossRef
import com.wecanteen105.core.database.model.tv.TvEntity
import com.wecanteen105.core.database.model.tv.TvWithCastEntities
import com.wecanteen105.core.database.model.tv.TvWithCrewEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query(
        value = """
            SELECT * FROM tvs
            WHERE id = :id
        """
    )
    fun getTvEntity(id: Int): Flow<TvEntity>

    @Query(value = "SELECT * FROM tvs")
    fun getTvEntities(): Flow<List<TvEntity>>

    @Query(value = "SELECT * FROM tvs")
    suspend fun getOneOffTvEntities(): List<TvEntity>

    @Query(
        value = """
            SELECT * FROM tvs
            WHERE id IN (:ids)
        """
    )
    fun getTvEntities(ids: Set<Int>):Flow<List<TvEntity>>

    /**
     * Inserts [entities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTvs(entities: List<TvEntity>): List<Long>

    /**
     * Inserts or updates [entities] in the db under the specified primary keys.
     */
    @Upsert
    suspend fun upsertTvs(entities: List<TvEntity>)

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM tvs
            WHERE id IN (:ids)
        """
    )
    suspend fun deleteTvs(ids: Set<Int>)

    // CrossRef
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTvCastCrossRefEntities(tvCastCrossRef:List<TvCastCrossRef>)

    @Query("DELETE FROM tvs_casts WHERE tv_id IN (:tvIds) OR cast_id IN (:castIds)")
    suspend fun deleteTvCastCrossRefEntities(tvIds: Set<Int> = emptySet(), castIds: Set<Int> = emptySet())

    @Query("SELECT * FROM tvs_casts WHERE tv_id IN (:tvIds) OR cast_id IN (:castIds)")
    suspend fun getTvCastCrossRefEntities(tvIds: Set<Int> = emptySet(), castIds: Set<Int> = emptySet()): List<TvCastCrossRef>

    // Cast
    /**
     * Get the tv associated with a list of cast
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM tvs
            WHERE id = :tvId
        """
    )
    fun getTvWithCastEntities(tvId: Int): Flow<TvWithCastEntities>

    @Transaction
    @Query(
        value = """
            SELECT * FROM tvs
            WHERE id = :tvId
        """
    )
    suspend fun getOneOffTvWithCastEntities(tvId:Int):List<TvWithCastEntities>

    // Crew
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTvCrewCrossRefEntities(tvCrewCrossRef:List<TvCrewCrossRef>)

    @Query("DELETE FROM tvs_crews WHERE tv_id IN (:tvIds) OR crew_id IN (:crewIds)")
    suspend fun deleteTvCrewCrossRefEntities(tvIds: Set<Int> = emptySet(), crewIds: Set<Int> = emptySet())

    @Query("SELECT * FROM tvs_crews WHERE tv_id IN (:tvIds) OR crew_id IN (:crewIds)")
    suspend fun getTvCrewCrossRefEntities(tvIds: Set<Int> = emptySet(), crewIds: Set<Int> = emptySet()): List<TvCrewCrossRef>

    @Transaction
    @Query("SELECT * FROM tvs WHERE id = :tvId")
    fun getTvWithCrewEntities(tvId: Int): Flow<TvWithCrewEntities>

    @Transaction
    @Query("SELECT * FROM tvs WHERE id = :tvId")
    suspend fun getOneOffTvWithCrewEntities(tvId:Int):List<TvWithCrewEntities>
}