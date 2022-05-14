package com.suleyman.notesapp.db.converters

import androidx.room.TypeConverter

class TagsConverter {
    @TypeConverter
    fun fromTags(data: String): List<String> = data.split(",").map { it }

    @TypeConverter
    fun toTags(tags: List<String>): String = tags.joinToString(separator = ",")
}