package com.org.rickandmorty.features.home.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.org.rickandmorty.features.home.HomeRoute
import com.org.rickandmorty.navigation.Routes
import com.org.rickandmorty.utils.dropLastRouteWhile

/**
 * As we want to simulate nav graph behavior, we need to ensure that the Home route is always at
 * the end of the back stack.This function will drop all routes until it finds the Home route, and
 * if it's not the last route, it will add it to the end of the back stack.
 */
fun SnapshotStateList<Routes>.navigateToHome() {
    dropLastRouteWhile { it != Routes.Home }

    if (isEmpty() || last() != Routes.Home) {
        add(Routes.Home)
    }
}

fun EntryProviderBuilder<*>.homeRoute() {
    entry<Routes.Home> {
        HomeRoute()
    }
}
