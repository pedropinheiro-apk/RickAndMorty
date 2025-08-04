package com.org.rickandmorty.data.local.dataSource

import androidx.paging.PagingSource
import com.org.rickandmorty.data.dto.CharacterWithFavorite
import com.org.rickandmorty.data.local.dao.CharacterDao
import com.org.rickandmorty.data.local.entity.CharacterEntity
import javax.inject.Inject

interface CharacterLocalDataSource {
    fun getCharactersPagingSource(): PagingSource<Int, CharacterWithFavorite>
    suspend fun insertCharacters(characters: List<CharacterEntity>)
    suspend fun clearCharacters()
}

class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : CharacterLocalDataSource {

    override fun getCharactersPagingSource() = characterDao.getCharactersWithFavorites()

    override suspend fun clearCharacters() = characterDao.clearAll()

    override suspend fun insertCharacters(
        characters: List<CharacterEntity>,
    ) = characterDao.insertAll(characters)
}
