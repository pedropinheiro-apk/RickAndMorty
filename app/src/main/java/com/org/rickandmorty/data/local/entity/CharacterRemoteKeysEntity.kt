package com.org.rickandmorty.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "character_remote_keys",
    indices = [Index(value = ["characterId"], unique = true)]
)
data class CharacterRemoteKeysEntity(
    @PrimaryKey
    val characterId: Long,
    @ColumnInfo
    val prevKey: Int?,
    @ColumnInfo
    val nextKey: Int?
)
