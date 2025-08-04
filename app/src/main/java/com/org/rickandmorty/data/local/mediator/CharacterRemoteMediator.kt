@file:OptIn(ExperimentalPagingApi::class)

package com.org.rickandmorty.data.local.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.org.rickandmorty.data.dto.CharacterWithFavorite
import com.org.rickandmorty.data.local.dao.CharacterRemoteKeysDao
import com.org.rickandmorty.data.local.dataSource.CharacterLocalDataSource
import com.org.rickandmorty.data.local.database.RickAndMortyDatabase
import com.org.rickandmorty.data.local.entity.CharacterRemoteKeysEntity
import com.org.rickandmorty.data.remote.CharacterApi
import retrofit2.HttpException
import java.io.IOException

class CharacterRemoteMediator(
    private val api: CharacterApi,
    private val localDataSource: CharacterLocalDataSource,
    private val remoteKeysDao: CharacterRemoteKeysDao,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, CharacterWithFavorite>() {

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterWithFavorite>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        try {
            val response = api.getCharacters(page)
            val characters = response.results.orEmpty().map { it.toEntity() }
            val endOfPaginationReached = response.info?.next == null

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    localDataSource.clearCharacters()
                }

                val keys = characters.map { character ->
                    CharacterRemoteKeysEntity(
                        characterId = character.id,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (endOfPaginationReached) null else page + 1
                    )
                }
                remoteKeysDao.insertAll(keys)
                localDataSource.insertCharacters(characters)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterWithFavorite>
    ): CharacterRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { characterWithFavorite ->
                remoteKeysDao.remoteKeysById(characterWithFavorite.character.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterWithFavorite>
    ): CharacterRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { characterWithFavorite ->
                remoteKeysDao.remoteKeysById(characterWithFavorite.character.id)
            }
    }
}
