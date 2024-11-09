package com.wecanteen105.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wecanteen105.core.database.model.RecentSearchQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchQueryDao {
    @Query(
        value = """
            SELECT * FROM recent_search_queries
            ORDER BY queriedDate DESC 
            LIMIT :limit
        """
    )
    fun getRecentSearchQueryEntities(limit: Int): Flow<List<RecentSearchQueryEntity>>

    @Upsert
    suspend fun insertOrReplaceRecentSearchQuery(query: RecentSearchQueryEntity)

    @Query(value = "DELETE FROM recent_search_queries")
    suspend fun clearRecentSearchQueries()
}