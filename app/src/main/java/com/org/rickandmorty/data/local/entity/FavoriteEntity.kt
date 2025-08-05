package com.org.rickandmorty.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites",
    indices = [Index(value = ["id"], unique = true)],
)
data class FavoriteEntity(
    @PrimaryKey val id: Long
)
