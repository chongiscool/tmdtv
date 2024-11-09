package com.wecanteen105.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.Video

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    val id: String,
    val iso31661: String,
    val iso6391: String,
    val key: String,
    val name: String,
    val official: Boolean,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    val site: String,
    val size: Int,
    val type: String,
    /**
     * Saved tvDetail or movieDetail id in this table
     */
    @ColumnInfo(name = "movieTvDetailId")
    val movieTvDetailId:Int,
)

fun VideoEntity.asExternalModel() = Video(
    id = id,
    iso31661 = iso31661,
    iso6391 = iso6391,
    key = key,
    name = name,
    official = official,
    publishedAt = publishedAt,
    site = site,
    size = size,
    type = type,
)