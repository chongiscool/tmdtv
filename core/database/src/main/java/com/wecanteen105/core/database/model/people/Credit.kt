package com.wecanteen105.core.database.model.people

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wecanteen105.core.model.Cast
import com.wecanteen105.core.model.Crew

@Entity(tableName = "casts")
data class CastEntity(
    val adult: Boolean,
    val character: String,
    @ColumnInfo(name = "credit_id")
    val creditId: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @ColumnInfo(name ="profile_path")
    val profilePath: String,
    // Add this field for auto migration testing
//    val country: String,
)

@Entity(tableName = "crews")
data class CrewEntity(
    val adult: Boolean,
    @ColumnInfo(name = "credit_id")
    val creditId: String,
    val department: String,
    val gender: Int,
    @PrimaryKey
    val id: Int,
    val job: String,
    @ColumnInfo(name = "known_for_department")
    val knownForDepartment: String,
    val name: String,
    @ColumnInfo(name = "original_name")
    val originalName: String,
    val popularity: Double,
    @ColumnInfo(name = "profile_path")
    val profilePath: String,
//    val country: String,
)


fun CastEntity.asExternalModel() = Cast(
    adult = adult,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
)

fun CrewEntity.asExternalModel() = Crew(
    adult = adult,
    creditId = creditId,
    department = department,
    gender = gender,
    id = id,
    job = job,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
)

