package com.wecanteen105.core.model

// We can get `now playing` and `upcoming` movies from the same endpoint, but ignore some property like: dates { maximum, minimum }

/**
 * List of media types: Movies(popular, top rated, movie recommendations), TV shows(tv recommendations)
 */
//data class MovieTvList<T>(
//    val page: Int,
//    val results: List<T> = emptyList(),
//    val totalPages: Int,
//    val totalResults: Int,
//)

data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class Tv(
    val adult: Boolean,
    val backdropPath: String?,
    val firstAirDate: String,
    val id: Int,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)