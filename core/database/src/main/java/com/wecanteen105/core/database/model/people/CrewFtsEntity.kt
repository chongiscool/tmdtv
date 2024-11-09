package com.wecanteen105.core.database.model.people

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

/**
 * Use name of cast OR overview to search specific cast, then click to see cast detail.
 */
@Entity(tableName = "crewsFts")
@Fts4
data class CrewFtsEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
)

fun CrewEntity.asCrewFtsEntity() = CrewFtsEntity(
    rowId = id,
    name = name,
    originalName = originalName,
)