package com.wecanteen105.core.model

/**
 * videos of both Movie and TV are same structure
 */
data class MovieTvVideo(
    val id: Int,
    val results: List<Video>
)

/**
 * Video contains these types: Trailer/Teaser/Clip/Featurette/Behind the Scenes
 */
data class Video(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
)