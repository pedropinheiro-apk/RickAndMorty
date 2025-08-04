package com.org.rickandmorty.di

import com.org.rickandmorty.data.repository.AlwaysAuthenticatedAuthRepositoryImpl
import com.org.rickandmorty.domain.repository.AuthRepository
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
}
