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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.features.characterDetails.CharacterDetailsRoute
import com.org.rickandmorty.features.home.ui.CharacterItem
import com.org.rickandmorty.features.home.ui.rememberPaletteCollection
import com.org.rickandmorty.utils.isLoading
import com.org.rickandmorty.utils.isLoadingMore
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val charactersState = viewModel.charactersPager.collectAsLazyPagingItems()

    HomeScreen(
        modifier = modifier,
        charactersState = charactersState,
        onToggleFavoriteClicked = viewModel::toggleFavorite,
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    charactersState: LazyPagingItems<Character> = emptyLazyPagingItems,
    onToggleFavoriteClicked: (Character) -> Unit = { },
) {
    val shouldShowDetails = remember { mutableStateOf(false) }
    var textPalette by remember { mutableStateOf<Int?>(null) }
    var backgroundPalette by remember { mutableStateOf<Int?>(null) }
    var selectedCharacter by remember { mutableStateOf<Character?>(null) }

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
                    onPaletteReady = palette::setBackgroundPalette,
                    textPalette = palette.getTextPalette(character.id),
                    backgroundPalette = palette.getBackgroundPalette(character.id),
                    onToggleFavoriteClicked = { onToggleFavoriteClicked(character) },
                    onClick = {
                        selectedCharacter = character
                        textPalette = palette.getTextPalette(character.id)
                        backgroundPalette = palette.getBackgroundPalette(character.id)
                        shouldShowDetails.value = true
                    },
                )
            }
        }

        if (isLoading || isLoadingMore) {
            item {
                LoadingIndicator()
            }
        }
    }

    if (shouldShowDetails.value) {
        CharacterDetailsRoute(
            textPalette = textPalette,
            character = selectedCharacter,
            backgroundPalette = backgroundPalette,
            onDismissRequest = { shouldShowDetails.value = false },
        )
    }
}

private val emptyLazyPagingItems: LazyPagingItems<Character>
    @Composable
    get() = flowOf(PagingData.empty<Character>()).collectAsLazyPagingItems()
