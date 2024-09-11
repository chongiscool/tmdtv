package com.wecanteen105.core.model

// We can get `now playing` and `upcoming` movies from the same endpoint, but ignore some property like: dates { maximum, minimum }

/**
 * List of media types: Movies(popular, top rated, movie recommendations), TV shows(tv recommendations)
 */
data class TmdbMediaList<T>(
    val page: Int,
    val results: List<T> = emptyList(),
    val total_pages: Int,
    val total_results: Int,
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class Tv(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)