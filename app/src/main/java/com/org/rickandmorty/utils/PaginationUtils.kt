package com.org.rickandmorty.utils

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

val LazyPagingItems<*>.isLoading: Boolean
    get() = loadState.refresh is LoadState.Loading

val LazyPagingItems<*>.isLoadingMore: Boolean
    get() = loadState.append is LoadState.Loading
