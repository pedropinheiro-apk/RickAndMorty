package com.org.rickandmorty.domain.usecase

fun interface ToggleCharacterFavoriteUseCase : suspend (Long) -> Result<Unit>
