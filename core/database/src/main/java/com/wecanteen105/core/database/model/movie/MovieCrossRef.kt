package com.wecanteen105.core.database.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CrewEntity

/**
 * Why use [MovieEntity] NOT [MovieDetailEntity], when use a cast to fetch all this cast movies, which is a list of [MovieEntity].
 * If I click one movie, then go to its MovieDetail.
 */
@Entity(
    tableName = "movies_crews",
    primaryKeys = ["movie_id", "crew_id"],
    foreignKeys = [
        ForeignKey(
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            entity = MovieEntity::class,
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            parentColumns = ["id"],
            childColumns = ["crew_id"],
            entity = CrewEntity::class,
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [
        Index(value =["movie_id"]),
        Index(value =["crew_id"]),
    ]
)
data class MovieCrewCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "crew_id")
    val crewId:Int,
)

@Entity(
    tableName = "movies_casts",
    primaryKeys = ["movie_id", "cast_id"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["id"],
            childColumns = ["movie_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CastEntity::class,
            parentColumns = ["id"],
            childColumns = ["cast_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["movie_id"]),
        Index(value = ["cast_id"]),
    ],
)
data class MovieCastCrossRef(
    @ColumnInfo(name = "movie_id")
    val movieId: Int,
    @ColumnInfo(name = "cast_id")
    val castId:Int,
)



