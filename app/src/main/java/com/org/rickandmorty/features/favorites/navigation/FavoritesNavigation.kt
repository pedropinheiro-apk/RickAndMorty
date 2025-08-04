package com.org.rickandmorty.features.favorites.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.org.rickandmorty.features.favorites.FavoritesRoute
import com.org.rickandmorty.navigation.Routes

fun SnapshotStateList<Routes>.navigateToFavorites() {
    if (Routes.Favorites !in this) {
        add(Routes.Favorites)
    }
}

fun EntryProviderBuilder<*>.favoritesRoute() {
    entry<Routes.Favorites> {
        FavoritesRoute()
    }
}
