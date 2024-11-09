package com.wecanteen105.core.database.model.people

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "castsFts")
@Fts4
data class CastFtsEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
)

fun CastEntity.asCastFtsEntity() = CastFtsEntity(
    rowId = id,
    name = name,
    originalName = originalName,
)