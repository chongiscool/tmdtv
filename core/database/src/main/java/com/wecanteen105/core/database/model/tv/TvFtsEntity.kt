package com.wecanteen105.core.database.model.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

/**
 * Use name of tv OR overview to search specific tv, then click to see tv detail.
 */
@Entity(tableName = "tvsFts")
@Fts4
data class TvFtsEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "overview")
    val overview: String,
)

fun TvEntity.asFtsEntity() = TvFtsEntity(
    rowId = id,
    name = name,
    overview = overview,
)