package com.org.rickandmorty.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.org.rickandmorty.domain.model.Character

interface CharacterRepository {
    fun getCharacterPager(): Flow<PagingData<Character>>
    fun getFavoriteCharactersPager(): Flow<PagingData<Character>>
    suspend fun toggleCharacterFavorite(id: Long): Result<Unit>
}
