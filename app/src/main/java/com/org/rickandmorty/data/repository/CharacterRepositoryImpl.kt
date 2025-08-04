@file:OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)

package com.org.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.org.rickandmorty.data.local.dao.CharacterRemoteKeysDao
import com.org.rickandmorty.data.local.dataSource.CharacterLocalDataSource
import com.org.rickandmorty.data.local.dataSource.FavoritesLocalDataSource
import com.org.rickandmorty.data.local.database.RickAndMortyDatabase
import com.org.rickandmorty.data.local.mediator.CharacterRemoteMediator
import com.org.rickandmorty.data.remote.CharacterApi
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi,
    private val database: RickAndMortyDatabase,
    private val remoteKeysDao: CharacterRemoteKeysDao,
    private val localDataSource: CharacterLocalDataSource,
    private val favoriteLocalDataSource: FavoritesLocalDataSource,
) : CharacterRepository {

    private val pagingConfig by lazy {
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 60,
            prefetchDistance = 10,
            enablePlaceholders = false,
        )
    }

    private val mediator by lazy {
        CharacterRemoteMediator(
            api = api,
            database = database,
            remoteKeysDao = remoteKeysDao,
            localDataSource = localDataSource
        )
    }

    override fun getCharacterPager(): Flow<PagingData<Character>> {
        val pagingSourceFactory = { localDataSource.getCharactersPagingSource() }

        return Pager(
            config = pagingConfig,
            remoteMediator = mediator,
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData ->
            pagingData.map {
                it.character.toCharacter().copy(isFavorite = it.isFavorite)
            }
        }
    }

    override suspend fun toggleCharacterFavorite(id: Long): Result<Unit> {
        return try {
            favoriteLocalDataSource.toggleCharacterFavorite(id)
            Result.success(Unit)
        } catch (ex: IOException) {
            Result.failure(ex)
        }
    }
}
