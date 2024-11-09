package com.wecanteen105.core.database.model.movie

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CrewEntity

/**
 * Use a movieId or tvId to query Credit, and save part of Crews and Casts.
 */
data class MovieWithCastEntities(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieCastCrossRef::class,
            parentColumn = "movie_id",
            entityColumn = "cast_id"
        ),
    )
    val casts:List<CastEntity>,
)

/**
 * Use a cast(eg: Leonardo) to query all his/her movies.
 */
data class CastWithMovieEntities(
    @Embedded
    val cast: CastEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieCastCrossRef::class,
            parentColumn = "cast_id",
            entityColumn = "movie_id",
        )
    )
    val movies: List<MovieEntity>,
)

/**
 * use a movieId or tvId to query Credit, and save part of Crews and Casts.
 */
data class MovieWithCrews(
    @Embedded
    val movie: MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieCrewCrossRef::class,
            parentColumn = "movie_id",
            entityColumn = "crew_id"
        ),
    )
    val crews:List<CrewEntity>,
)

/**
 * Use a crew like director(eg: Nolan) to query all his/her movies.
 */
data class CrewWithMovies(
    @Embedded
    val crew: CrewEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieCrewCrossRef::class,
            parentColumn = "crew_id",
            entityColumn = "movie_id",
        ),
    )
    val movies:List<MovieEntity>,
)