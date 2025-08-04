package com.org.rickandmorty.data.local.dataSource

import com.org.rickandmorty.data.local.dao.FavoritesDao
import com.org.rickandmorty.data.local.entity.FavoriteEntity
import javax.inject.Inject

interface FavoritesLocalDataSource {
    suspend fun toggleCharacterFavorite(id: Long)
}

class FavoritesLocalDataSourceImpl @Inject constructor(
    private val favoritesDao: FavoritesDao,
) : FavoritesLocalDataSource {
    override suspend fun toggleCharacterFavorite(id: Long) {
        if (favoritesDao.isFavorite(id)) {
            favoritesDao.removeFavorite(id)
            return
        }

        favoritesDao.addFavorite(FavoriteEntity(id))
    }
}
