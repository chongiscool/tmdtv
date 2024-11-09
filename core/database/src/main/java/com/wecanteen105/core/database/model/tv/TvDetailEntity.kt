package com.wecanteen105.core.database.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.TvDetail

@Entity(
    tableName = "tv_details"
)
data class TvDetailEntity(
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "in_production")
    val inProduction: Boolean,
    @ColumnInfo(name = "last_air_date")
    val lastAirDate: String,
    val name: String,
    @ColumnInfo(name = "next_episode_to_air")
    val nextEpisodeToAir: String?,
    @ColumnInfo(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @ColumnInfo(name = "number_of_seasons")
    val numberOfSeasons: Int,
    val overview: String,
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    val status: String,
    val tagline: String,
    val type: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
)

fun TvDetailEntity.asExternalModel() = TvDetail(
    adult = adult,
    backdropPath = backdropPath,
    episodeRunTime = episodeRunTime,
    firstAirDate = firstAirDate,
    homepage = homepage,
    id = id,
    inProduction = inProduction,
    lastAirDate = lastAirDate,
    name = name,
    nextEpisodeToAir = nextEpisodeToAir,
    numberOfEpisodes = numberOfEpisodes,
    numberOfSeasons = numberOfSeasons,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    status = status,
    tagline = tagline,
    type = type,
    voteAverage = voteAverage,
    voteCount = voteCount,
    seasons = listOf(),
)