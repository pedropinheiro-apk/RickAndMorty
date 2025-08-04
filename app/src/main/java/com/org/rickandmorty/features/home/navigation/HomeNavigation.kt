package com.org.rickandmorty.features.home.navigation

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.entry
import com.org.rickandmorty.features.home.HomeRoute
import com.org.rickandmorty.navigation.Routes

fun EntryProviderBuilder<*>.homeRoute() {
    entry<Routes.Home> {
        HomeRoute()
    }
}
