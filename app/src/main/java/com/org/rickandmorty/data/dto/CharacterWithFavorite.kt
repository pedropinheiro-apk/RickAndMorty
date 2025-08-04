package com.org.rickandmorty.data.dto

import androidx.room.Embedded
import com.org.rickandmorty.data.local.entity.CharacterEntity

data class CharacterWithFavorite(
    @Embedded val character: CharacterEntity,
    val isFavorite: Boolean
)
