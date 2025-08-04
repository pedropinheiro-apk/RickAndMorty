package com.org.rickandmorty.di

import com.org.rickandmorty.domain.repository.AuthRepository
import com.org.rickandmorty.domain.repository.CharacterRepository
import com.org.rickandmorty.domain.usecase.GetCharactersPagerUseCase
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
    ): GetSessionStateUseCase = GetSessionStateUseCase(repository::getSessionState)

    @Provides
    fun provideGetCharacterPagerUseCase(
        repository: CharacterRepository
    ): GetCharactersPagerUseCase = GetCharactersPagerUseCase(repository::getCharacterPager)
}
