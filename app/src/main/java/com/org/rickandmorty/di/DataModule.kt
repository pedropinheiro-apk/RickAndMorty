package com.org.rickandmorty.di

import com.org.rickandmorty.data.local.dataSource.CharacterLocalDataSource
import com.org.rickandmorty.data.local.dataSource.CharacterLocalDataSourceImpl
import com.org.rickandmorty.data.repository.AlwaysAuthenticatedAuthRepositoryImpl
import com.org.rickandmorty.data.repository.CharacterRepositoryImpl
import com.org.rickandmorty.domain.repository.AuthRepository
import com.org.rickandmorty.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun bindsAuthRepository(
        authRepository: AlwaysAuthenticatedAuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    internal abstract fun bindsCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository

    @Binds
    @Singleton
    internal abstract fun bindsCharacterLocalDataSource(
        characterLocalDataSource: CharacterLocalDataSourceImpl,
    ): CharacterLocalDataSource
}
