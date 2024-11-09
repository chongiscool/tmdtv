package com.wecanteen105.core.model

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: String?,
    val budget: Int,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class TvDetail(
    val adult: Boolean,
    val backdropPath: String?,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val lastAirDate: String,
    val name: String,
    val nextEpisodeToAir: String?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class CreatedBy(
    val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val originalName: String,
    val profilePath: String
)

data class LastEpisodeToAir(
    val airDate: String,
    val episodeNumber: Int,
    val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int
)

data class Network(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class Season(
    val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
    val voteAverage: Double
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCountry(
    /**
     * eg: US, CN, FR
     */
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val englishName: String,
    /**
     * eg: zh, en, fr
     */
    val iso6391: String,
    val name: String
)
