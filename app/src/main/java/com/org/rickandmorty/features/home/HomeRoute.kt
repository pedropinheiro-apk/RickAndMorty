@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.org.rickandmorty.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.org.rickandmorty.domain.model.Character
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
    val isLoading = charactersState.isLoading
    val isLoadingMore = charactersState.isLoadingMore

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (isLoading) Arrangement.Center else Arrangement.Top,
    ) {
        if (isLoading) {
            item {
                LoadingIndicator()
            }

            return@LazyColumn
        }

        items(
            count = charactersState.itemCount,
            key = { index -> charactersState.peek(index)?.id ?: index }
        ) { index ->
            val char = charactersState[index]

            if (char != null) {
                Text(char.name)
            }
        }

        if (isLoadingMore) {
            item {
                LoadingIndicator()
            }
        }
    }
}
