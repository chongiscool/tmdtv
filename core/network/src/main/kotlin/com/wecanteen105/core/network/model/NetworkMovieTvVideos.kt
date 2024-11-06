package com.wecanteen105.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Network representation of MovieTvVideos
 */
@Serializable
data class NetworkMovieTvVideos(
    @SerialName("id")
    val id: Int,
    @SerialName("results")
    val results: List<NetworkVideo>
)

@Serializable
data class NetworkVideo(
    @SerialName("id")
    val id: String,
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    /**
     * video url is composed of youtube based url + key
     */
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("official")
    val official: Boolean,
    @SerialName("published_at")
    val publishedAt: String,
    /**
     * Typical site values are: "YouTube".
     */
    @SerialName("site")
    val site: String,
    @SerialName("size")
    val size: Int,
    /**
     * The type of the video. Possible values are: "Trailer", "Teaser", "Clip", "Featurette", "Behind the Scenes".
     */
    @SerialName("type")
    val type: String?
)