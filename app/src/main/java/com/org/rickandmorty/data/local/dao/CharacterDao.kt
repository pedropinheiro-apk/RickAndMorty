package com.org.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.org.rickandmorty.data.local.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getCharactersPagingSource(): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}
