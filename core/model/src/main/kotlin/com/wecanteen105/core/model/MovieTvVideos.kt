package com.wecanteen105.core.model

/**
 * videos of both Movie and TV are same structure
 */
//data class MovieTvVideos(
//    val id: Int,
//    val results: List<Video>
//)

/**
 * Video contains these types: Trailer/Teaser/Clip/Featurette/Behind the Scenes
 */
data class Video(
    val id: String,
    val iso31661: String,
    val iso6391: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val publishedAt: String,
    val site: String,
    val size: Int,
    val type: String
)