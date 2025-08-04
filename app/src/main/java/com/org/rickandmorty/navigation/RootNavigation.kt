@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.org.rickandmorty.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.org.rickandmorty.features.favorites.navigation.favoritesRoute
import com.org.rickandmorty.features.favorites.navigation.navigateToFavorites
import com.org.rickandmorty.features.home.navigation.homeRoute
import com.org.rickandmorty.features.home.navigation.navigateToHome

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
) {
    val backStack = remember { mutableStateListOf<Routes>(Routes.Home) }
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            BottomNavigationBar(
                scrollBehavior = scrollBehavior,
                selectedRoute = backStack.last(),
                onHomeClicked = backStack::navigateToHome,
                onFavoriteClicked = backStack::navigateToFavorites,
            )
        },
        content = { innerPadding ->
            NavDisplay(
                modifier = modifier.padding(innerPadding),
                backStack = backStack,
                entryProvider = entryProvider {
                    homeRoute()
                    favoritesRoute()
                }
            )
        }
    )
}
