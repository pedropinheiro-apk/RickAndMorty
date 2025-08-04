package com.org.rickandmorty.di

import com.org.rickandmorty.domain.repository.AuthRepository
import com.org.rickandmorty.domain.usecase.GetSessionStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetSessionStateUseCase(
        repository: AuthRepository
    ): GetSessionStateUseCase {
        return GetSessionStateUseCase(repository::getSessionState)
    }
}
