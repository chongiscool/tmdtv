package com.wecanteen105.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.wecanteen105.core.model.MovieDetail


/**
 * Network representation of [MovieDetail]
 */
@Serializable
data class NetworkMovieDetail(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: String?,
    @SerialName("budget")
    val budget: Int,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("revenue")
    val revenue: Int,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkTvDetail(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int?>,
    @SerialName("first_air_date")
    val firstAirDate: String,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("id")
    val id: Int,
    @SerialName("in_production")
    val inProduction: Boolean,
    @SerialName("languages")
    val lastAirDate: String,
    @SerialName("name")
    val name: String,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: String?,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("seasons")
    val seasons: List<NetworkSeason>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("type")
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkCreatedBy(
    @SerialName("credit_id")
    val creditId: String,
    @SerialName("gender")
    val gender: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("profile_path")
    val profilePath: String?
)

@Serializable
data class NetworkGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

@Serializable
data class NetworkLastEpisodeToAir(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    @SerialName("episode_type")
    val episodeType: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("production_code")
    val productionCode: String,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("show_id")
    val showId: Int,
    @SerialName("still_path")
    val stillPath: String?,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class NetworkProductionCompany(
    @SerialName("id")
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class NetworkProductionCountry(
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("name")
    val name: String
)

@Serializable
data class NetworkSeason(
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_count")
    val episodeCount: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("vote_average")
    val voteAverage: Double
)

@Serializable
data class NetworkSpokenLanguage(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("name")
    val name: String
)