package com.org.rickandmorty.domain.usecase

import androidx.paging.PagingData
import com.org.rickandmorty.domain.model.Character
import kotlinx.coroutines.flow.Flow

fun interface GetFavoritesCharactersPagerUseCase : () -> Flow<PagingData<Character>>
