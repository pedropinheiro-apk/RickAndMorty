package com.org.rickandmorty.di

import com.org.rickandmorty.domain.repository.AuthRepository
import com.org.rickandmorty.domain.repository.CharacterRepository
import com.org.rickandmorty.domain.usecase.GetCharactersPagerUseCase
import com.org.rickandmorty.domain.usecase.GetFavoritesCharactersPagerUseCase
import com.org.rickandmorty.domain.usecase.GetSessionStateUseCase
import com.org.rickandmorty.domain.usecase.ToggleCharacterFavoriteUseCase
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

    @Provides
    fun provideGetFavoritesCharacterPagerUseCase(
        repository: CharacterRepository
    ): GetFavoritesCharactersPagerUseCase =
        GetFavoritesCharactersPagerUseCase(repository::getFavoriteCharactersPager)

    @Provides
    fun provideToggleCharacterFavoriteUseCase(
        repository: CharacterRepository
    ): ToggleCharacterFavoriteUseCase =
        ToggleCharacterFavoriteUseCase(repository::toggleCharacterFavorite)
}
