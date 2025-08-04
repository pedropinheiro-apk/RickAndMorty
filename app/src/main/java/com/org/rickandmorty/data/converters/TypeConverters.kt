package com.org.rickandmorty.data.converters

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String? = value?.joinToString(",")

    @TypeConverter
    fun toStringList(value: String?): List<String>? = value?.split(",")?.map { it.trim() }
}
