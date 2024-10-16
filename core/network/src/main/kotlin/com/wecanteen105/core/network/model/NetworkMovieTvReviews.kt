package com.wecanteen105.core.network.model
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import com.wecanteen105.core.model.MovieTvReviews

/**
 * Network representation of [MovieTvReviews]
 */
@Serializable
data class NetworkMovieTvReviews(
    @SerialName("id")
    val id: Int,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<NetworkResult>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class NetworkResult(
    @SerialName("author")
    val author: String,
    @SerialName("author_details")
    val authorDetails: NetworkAuthorDetails,
    /**
     * The content of the review.
     */
    @SerialName("content")
    val content: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("id")
    val id: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class NetworkAuthorDetails(
    @SerialName("avatar_path")
    val avatarPath: String?,
    @SerialName("name")
    val name: String,
    @SerialName("rating")
    val rating: Double,
    @SerialName("username")
    val username: String
)