package com.org.rickandmorty.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.org.rickandmorty.data.remote.CharacterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideCharacterApi(
        retrofit: Retrofit,
    ): CharacterApi = retrofit.create(CharacterApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClientWithoutAccessToken(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
    }
}
