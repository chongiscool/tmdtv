package com.wecanteen105.core.database.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey


@Entity(tableName = "moviesFts")
@Fts4
data class MovieFtsEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Int,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "title")
    val title: String,
)

fun MovieEntity.asFtsEntity() = MovieFtsEntity(
    rowId = id,
    overview = overview,
    title = title,
)

