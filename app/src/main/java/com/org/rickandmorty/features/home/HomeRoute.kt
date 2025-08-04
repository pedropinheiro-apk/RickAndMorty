@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.org.rickandmorty.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.features.home.ui.CharacterItem
import com.org.rickandmorty.features.home.ui.rememberPaletteCollection
import com.org.rickandmorty.utils.isLoading
import com.org.rickandmorty.utils.isLoadingMore
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val charactersState = viewModel.charactersPager.collectAsLazyPagingItems()

    HomeScreen(
        modifier = modifier,
        charactersState = charactersState,
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    charactersState: LazyPagingItems<Character> = flowOf(PagingData.empty<Character>()).collectAsLazyPagingItems(),
) {
    val palette = rememberPaletteCollection()
    val isLoading = charactersState.isLoading
    val isLoadingMore = charactersState.isLoadingMore

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = if (isLoading) Arrangement.Center else Arrangement.spacedBy(16.dp),
    ) {
        items(
            count = charactersState.itemCount,
            key = charactersState.itemKey { it.id },
            contentType = charactersState.itemContentType { "characters" },
        ) { index ->
            val character = charactersState[index]

            if (character != null) {
                CharacterItem(
                    modifier = Modifier.fillMaxSize(0.15f),
                    character = character,
                    onTextPaletteReady = palette::setTextPalette,
                    textPalette = palette.getTextPalette(character.id),
                    onPaletteReady = palette::setBackgroundPalette,
                    backgroundPalette = palette.getBackgroundPalette(character.id),
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
