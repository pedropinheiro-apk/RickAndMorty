package com.org.rickandmorty.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Routes : NavKey {
    @Serializable
    data object Home : Routes

    @Serializable
    data class CharacterDetail(val id: Int) : Routes
}
