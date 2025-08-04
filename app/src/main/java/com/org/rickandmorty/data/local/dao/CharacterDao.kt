package com.org.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.org.rickandmorty.data.dto.CharacterWithFavorite
import com.org.rickandmorty.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getCharactersPagingSource(): PagingSource<Int, CharacterEntity>

    @Query(
        """
        SELECT c.*, 
        CASE WHEN f.id IS NOT NULL THEN 1 ELSE 0 END AS isFavorite
        FROM characters c
        LEFT JOIN favorites f ON c.id = f.id
        """
    )
    fun getCharactersWithFavorites(): PagingSource<Int, CharacterWithFavorite>

    @Query(
        """
        SELECT c.*, 
        1 AS isFavorite
        FROM characters c
        INNER JOIN favorites f ON c.id = f.id
        """
    )
    fun getFavoriteCharacters(): PagingSource<Int, CharacterWithFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}
