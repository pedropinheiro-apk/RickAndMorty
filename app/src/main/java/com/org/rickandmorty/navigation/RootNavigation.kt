package com.org.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.org.rickandmorty.features.home.navigation.homeRoute

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
) {
    val backStack = remember { mutableStateListOf<Routes>(Routes.Home) }

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = entryProvider {
            homeRoute()
        }
    )
}
