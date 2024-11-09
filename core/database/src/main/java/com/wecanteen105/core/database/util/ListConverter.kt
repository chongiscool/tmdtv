package com.wecanteen105.core.database.util

import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class ListIntConverter {
    @TypeConverter
    fun listToString(value: List<Int>?): String? =
       value?.let {
            Json.encodeToString(DataListInt(it))
        }


    @TypeConverter
    fun stringToList(string: String?): List<Int>? =
        string?.let {
            Json.decodeFromString<DataListInt>(it).value
        }
}

@Serializable
internal class DataListInt(val value: List<Int>?)
