package com.wecanteen105.core.database.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.Tv

@Entity(
    tableName = "tvs",
)
data class TvEntity(
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)

fun TvEntity.asExternalModel() = Tv(
    adult = adult,
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    id = id,
    name = name,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
)