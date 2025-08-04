package com.org.rickandmorty.domain.model

data class Character(
    val image: String,
    val gender: String,
    val species: String,
    val created: String,
    val origin: Location,
    val name: String,
    val location: Location,
    val episode: List<String>,
    val id: Long,
    val type: String,
    val url: String,
    val status: String,
)
