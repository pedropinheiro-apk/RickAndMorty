package com.org.rickandmorty.utils

import androidx.paging.compose.LazyPagingItems

val LazyPagingItems<*>.isLoading: Boolean
    get() = loadState.refresh is androidx.paging.LoadState.Loading

val LazyPagingItems<*>.isLoadingMore: Boolean
    get() = loadState.append is androidx.paging.LoadState.Loading
