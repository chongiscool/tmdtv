package com.wecanteen105.core.database.model.movie

import androidx.room.Embedded
import androidx.room.Relation
import com.wecanteen105.core.database.model.ReviewEntity
import com.wecanteen105.core.database.model.VideoEntity

data class MovieDetailWithReviews(
    @Embedded val entity: MovieDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieTvDetailId"
    )
    val reviews:List<ReviewEntity>
)

data class MovieDetailWithVideos(
    @Embedded val entity: MovieDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieTvDetailId"
    )
    val videos:List<VideoEntity>
)