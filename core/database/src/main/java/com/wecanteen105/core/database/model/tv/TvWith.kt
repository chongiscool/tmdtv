package com.wecanteen105.core.database.model.tv

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CrewEntity

/**
 * Use a tvId to query Credit, and save part of Crews and Casts.
 */
data class TvWithCastEntities(
    @Embedded val tv: TvEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TvCastCrossRef::class,
            parentColumn = "tv_id",
            entityColumn = "cast_id",
        ),
    ) val casts: List<CastEntity>,
)

/**
 * Use a cast like Leonardo to query all his/her tvs.
 */
data class CastWithTvEntities(
    @Embedded val cast: CastEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TvCastCrossRef::class,
            parentColumn = "cast_id",
            entityColumn = "tv_id",
        )
    ) val tvs: List<TvEntity>
)

/**
 * Use a tvId to query Credit, and save part of Crews and Casts.
 */
data class TvWithCrewEntities(
    @Embedded val tv: TvEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TvCrewCrossRef::class,
            parentColumn = "tv_id",
            entityColumn = "crew_id",
        ),
    ) val crews: List<CrewEntity>,
)

/**
 * Use a crew like a director to query all his/her tvs.
 */
data class CrewWithTvEntities(
    @Embedded val crew: CrewEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TvCrewCrossRef::class,
            parentColumn = "crew_id",
            entityColumn = "tv_id",
        )
    )
    val tvs:List<TvEntity>,
)