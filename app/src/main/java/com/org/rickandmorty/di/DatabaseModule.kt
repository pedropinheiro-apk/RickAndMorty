package com.org.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.org.rickandmorty.data.local.dao.CharacterDao
import com.org.rickandmorty.data.local.dao.CharacterRemoteKeysDao
import com.org.rickandmorty.data.local.database.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFreecastMobileDatabase(@ApplicationContext context: Context): RickAndMortyDatabase =
        Room.databaseBuilder(
            context,
            RickAndMortyDatabase::class.java,
            "RickAndMortyDatabase",
        ).fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideCharactersDao(
        database: RickAndMortyDatabase,
    ): CharacterDao = database.characterDao()

    @Provides
    @Singleton
    fun provideCharactersRemoteKeyDao(
        database: RickAndMortyDatabase,
    ): CharacterRemoteKeysDao = database.characterRemoteKeysDao()
}
