package com.org.rickandmorty.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.org.rickandmorty.data.local.dao.CharacterDao
import com.org.rickandmorty.data.local.dao.CharacterRemoteKeysDao
import com.org.rickandmorty.data.local.dao.FavoritesDao
import com.org.rickandmorty.data.local.entity.CharacterEntity
import com.org.rickandmorty.data.local.entity.FavoriteEntity
import com.org.rickandmorty.data.local.entity.CharacterRemoteKeysEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [CharacterEntity::class, CharacterRemoteKeysEntity::class, FavoriteEntity::class],
)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun favoritesDao(): FavoritesDao
    abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}
