package com.org.rickandmorty.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeRoute(modifier: Modifier = Modifier) {

    HomeScreen(modifier = modifier)
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier
) {

    Box(Modifier
        .fillMaxSize()
        .background(Color.Red))
}
