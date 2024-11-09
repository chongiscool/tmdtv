package com.wecanteen105.core.database

import com.wecanteen105.core.database.model.ReviewEntity
import com.wecanteen105.core.database.model.VideoEntity
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CrewEntity
import com.wecanteen105.core.database.model.tv.TvCastCrossRef
import com.wecanteen105.core.database.model.tv.TvCrewCrossRef
import com.wecanteen105.core.model.AuthorDetails

/**
 * Create a list of Cast entity for testing
 */
internal fun testCasts(ids: List<Int>): List<CastEntity> = ids.map {
    CastEntity(
        adult = false,
        gender = 1,
        id = it,
        knownForDepartment = "",
        name = "Cast $it",
        originalName = "",
        popularity = 1.0,
        profilePath = "",
        character = "",
        creditId = "",
        order = 1
    )
}

/**
 * Create a list of Crew entity for testing
 */
internal fun testCrews(ids:List<Int>):List<CrewEntity> = ids.map {
    CrewEntity(
        adult = false,
        creditId = "creditId",
        department = "",
        gender = 2,
        id = it,
        job = "job",
        knownForDepartment = "",
        name = "Crew $it",
        originalName = "",
        popularity = 1.0,
        profilePath = "",
    )
}

internal fun testTvCastCrossRefEntities(tvId: Int, castIds: List<Int>): List<TvCastCrossRef> =
    castIds.map { TvCastCrossRef(tvId, it) }

internal fun testTvCrewCrossRefEntities(tvId: Int, crewIds: List<Int>): List<TvCrewCrossRef> =
    crewIds.map { TvCrewCrossRef(tvId, it) }


internal fun testReviews(ids: List<String>, contents: List<String>, movieTvDetailId: Int) =
    ids.mapIndexed { index, id ->
        ReviewEntity(
            author = "",
            authorDetails = AuthorDetails(
                avatarPath = "",
                name = "",
                rating = 1.0,
                username = ""
            ),
            content = contents[index],
            createdAt = "",
            id = id,
            updatedAt = "",
            url = "url",
            movieTvDetailId = movieTvDetailId,
        )
    }

internal fun testVideos(ids: List<String>, names: List<String>, movieTvDetailId: Int) =
    ids.mapIndexed { index, id ->
        VideoEntity(
            id = id,
            iso31661 = "iso31661",
            iso6391 = "iso6391",
            key = "key",
            name = names[index],
            official = false,
            publishedAt = "publishedAt",
            site = "site",
            size = 1,
            type = "type",
            movieTvDetailId = movieTvDetailId
        )
    }

internal fun Long.toList(count:Int): List<Long> = (1..count).map { this }