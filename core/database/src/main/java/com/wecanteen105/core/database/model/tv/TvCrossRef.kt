package com.wecanteen105.core.database.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.wecanteen105.core.database.model.people.CastEntity
import com.wecanteen105.core.database.model.people.CrewEntity

/**
 * Why use [TvEntity] NOT [TvDetailEntity], when use a cast to fetch all this cast tvs, which is a list of [TvEntity].
 * If I click one Tv, then go to TvDetail.
 */
@Entity(
    tableName = "tvs_casts",
    primaryKeys = ["tv_id", "cast_id"],
    foreignKeys = [
        ForeignKey(
            entity = TvEntity::class,
            parentColumns = ["id"],
            childColumns = ["tv_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CastEntity::class,
            parentColumns = ["id"],
            childColumns = ["cast_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["tv_id"]),
        Index(value = ["cast_id"]),
    ],
)
data class TvCastCrossRef(
    @ColumnInfo(name = "tv_id")
    val tvId: Int,
    @ColumnInfo(name = "cast_id")
    val castId: Int,
)


@Entity(
    tableName = "tvs_crews",
    primaryKeys = ["tv_id", "crew_id"],
    foreignKeys = [
        ForeignKey(
            entity = TvEntity::class,
            parentColumns = ["id"],
            childColumns = ["tv_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = CrewEntity::class,
            parentColumns = ["id"],
            childColumns = ["crew_id"],
            /**
             * I think that: when delete in TvEntity, also delete rows in TvCrewCrossRef table
             */
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["tv_id"]),
        Index(value = ["crew_id"]),
    ]
)
data class TvCrewCrossRef(
    @ColumnInfo(name = "tv_id")
    val tvId: Int,
    @ColumnInfo(name = "crew_id")
    val crewId: Int,
)