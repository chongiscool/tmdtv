package com.wecanteen105.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.Review
import com.wecanteen105.core.model.AuthorDetails


@Entity(tableName = "reviews")
data class ReviewEntity(
    val author: String,
    @Embedded
    val authorDetails: AuthorDetails,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String,
    val url: String,
    /**
     * Saved tvDetail or movieDetail id in this table
     */
    @ColumnInfo(name = "movieTvDetailId")
    val movieTvDetailId: Int,
)

data class AuthorDetails(
    @ColumnInfo(name = "avatar_path")
    val avatarPath: String?,
    val name: String,
    val rating: Double?,
    val username: String
)

fun ReviewEntity.asExternalModel() = Review(
    author = author,
    authorDetails = AuthorDetails(
        avatarPath = authorDetails.avatarPath,
        name = authorDetails.name,
        rating = authorDetails.rating,
        username = authorDetails.username
    ),
    content = content,
    createdAt = createdAt,
    id = id,
    updatedAt = updatedAt,
    url = url,
)

