package com.org.rickandmorty.data.remote

import com.org.rickandmorty.data.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse
}
