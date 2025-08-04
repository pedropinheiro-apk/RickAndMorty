package com.org.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.org.rickandmorty.data.local.entity.CharacterRemoteKeysEntity

@Dao
interface CharacterRemoteKeysDao {

    @Query("SELECT * FROM character_remote_keys WHERE characterId = :id")
    suspend fun remoteKeysById(id: Long): CharacterRemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<CharacterRemoteKeysEntity>)

    @Query("DELETE FROM character_remote_keys")
    suspend fun clearRemoteKeys()
}
