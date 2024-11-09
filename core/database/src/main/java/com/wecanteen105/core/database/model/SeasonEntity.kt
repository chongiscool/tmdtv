package com.wecanteen105.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.Season

@Entity(
    tableName = "seasons"
)
data class SeasonEntity(
    @ColumnInfo(name = "air_date")
    val airDate: String,
    @ColumnInfo(name = "episode_count")
    val episodeCount: Int,
    @PrimaryKey
    val id: Int,
    val name: String,
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "season_number")
    val seasonNumber: Int,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "tvDetailId")
    val tvDetailId: Int,
)

fun SeasonEntity.asExternalModel() = Season(
    airDate = airDate,
    episodeCount = episodeCount,
    id = id,
    name = name,
    overview = overview,
    posterPath = posterPath,
    seasonNumber = seasonNumber,
    voteAverage = voteAverage,
)