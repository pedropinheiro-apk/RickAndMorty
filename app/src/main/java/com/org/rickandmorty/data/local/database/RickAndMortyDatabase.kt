package com.org.rickandmorty.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.org.rickandmorty.data.local.dao.CharacterDao
import com.org.rickandmorty.data.local.dao.CharacterRemoteKeysDao
import com.org.rickandmorty.data.local.entity.CharacterEntity
import com.org.rickandmorty.data.local.entity.CharacterRemoteKeysEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CharacterEntity::class, CharacterRemoteKeysEntity::class],
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}
