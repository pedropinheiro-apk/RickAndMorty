package com.org.rickandmorty.data.dto

import com.org.rickandmorty.data.local.entity.CharacterEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("results")
    val results: List<Result>? = null,
    @SerialName("info")
    val info: Info? = null,
)

@Serializable
data class Info(
    @SerialName("next")
    val next: String? = null,
    @SerialName("pages")
    val pages: Long? = null,
    @SerialName("prev")
    val prev: String? = null,
    @SerialName("count")
    val count: Long? = null,
)

@Serializable
data class Result(
    @SerialName("image")
    val image: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("species")
    val species: String? = null,
    @SerialName("created")
    val created: String? = null,
    @SerialName("origin")
    val origin: Location? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("episode")
    val episode: List<String>? = null,
    @SerialName("id")
    val id: Long? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("status")
    val status: String? = null,
) {
    fun toEntity() = CharacterEntity(
        id = id ?: 0L,
        image = image,
        gender = gender,
        species = species,
        created = created,
        origin = origin?.name,
        originUrl = origin?.url,
        name = name,
        location = location?.name,
        locationUrl = location?.url,
        episode = episode?.joinToString(separator = ","),
        type = type,
        url = url,
        status = status,
    )
}

@Serializable
data class Location(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
)
