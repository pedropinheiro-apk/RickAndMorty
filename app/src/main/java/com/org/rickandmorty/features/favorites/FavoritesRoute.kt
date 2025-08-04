@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.org.rickandmorty.features.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.features.favorites.ui.FavoriteCharacterItem
import com.org.rickandmorty.features.home.emptyLazyPagingItems
import com.org.rickandmorty.utils.isLoading
import com.org.rickandmorty.utils.isLoadingMore

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val charactersState = viewModel.charactersPager.collectAsLazyPagingItems()

    FavoritesScreen(
        modifier = modifier,
        charactersState = charactersState,
        onToggleFavoriteClicked = viewModel::toggleFavorite,
    )
}

@Composable
private fun FavoritesScreen(
    modifier: Modifier = Modifier,
    charactersState: LazyPagingItems<Character> = emptyLazyPagingItems,
    onToggleFavoriteClicked: (Character) -> Unit = { },
) {
    val isLoading = charactersState.isLoading
    val isLoadingMore = charactersState.isLoadingMore

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (isLoading) Arrangement.Center else Arrangement.spacedBy(16.dp),
    ) {
        items(
            count = charactersState.itemCount,
            key = charactersState.itemKey { it.id },
            contentType = charactersState.itemContentType { "characters" },
        ) { index ->
            val character = charactersState[index]

            if (character != null) {
                FavoriteCharacterItem(
                    modifier = Modifier.fillMaxSize(0.75f),
                    character = character,
                    onToggleFavoriteClicked = { onToggleFavoriteClicked(character) },
                )
            }
        }

        if (isLoading || isLoadingMore) {
            item {
                LoadingIndicator()
            }
        }
    }
}
