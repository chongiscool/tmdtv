package com.wecanteen105.core.database.model.tv

import androidx.room.Embedded
import androidx.room.Relation
import com.wecanteen105.core.database.model.ReviewEntity
import com.wecanteen105.core.database.model.SeasonEntity
import com.wecanteen105.core.database.model.VideoEntity
import com.wecanteen105.core.database.model.asExternalModel
import com.wecanteen105.core.model.TvDetail

data class TvDetailWithReviews(
    @Embedded val entity: TvDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieTvDetailId"
    )
    val reviews: List<ReviewEntity>,
)

data class TvDetailWithVideos(
    @Embedded val entity: TvDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieTvDetailId"
    )
    val videos:List<VideoEntity>
)

data class TvDetailWithSeasons(
    @Embedded val entity: TvDetailEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "tvDetailId"
    )
    val seasons: List<SeasonEntity>
)

fun TvDetailWithSeasons.asExternalModel() = TvDetail(
    adult = entity.adult,
    backdropPath = entity.backdropPath,
    episodeRunTime = entity.episodeRunTime,
    firstAirDate = entity.firstAirDate,
    homepage = entity.homepage,
    id = entity.id,
    inProduction = entity.inProduction,
    lastAirDate = entity.lastAirDate,
    name = entity.name,
    nextEpisodeToAir = entity.nextEpisodeToAir,
    numberOfEpisodes = entity.numberOfEpisodes,
    numberOfSeasons = entity.numberOfSeasons,
    overview = entity.overview,
    popularity = entity.popularity,
    posterPath = entity.posterPath,
    status = entity.status,
    tagline = entity.tagline,
    type = entity.type,
    voteAverage = entity.voteAverage,
    voteCount = entity.voteCount,
    seasons = seasons.map(SeasonEntity::asExternalModel),
)