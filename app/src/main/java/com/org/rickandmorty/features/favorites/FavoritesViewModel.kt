package com.org.rickandmorty.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.domain.usecase.GetFavoritesCharactersPagerUseCase
import com.org.rickandmorty.domain.usecase.ToggleCharacterFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    getFavoritesCharactersPagerUseCase: GetFavoritesCharactersPagerUseCase,
    private val toggleFavoriteUseCase: ToggleCharacterFavoriteUseCase,
) : ViewModel() {

    val charactersPager = getFavoritesCharactersPagerUseCase()
        .cachedIn(viewModelScope)

    fun toggleFavorite(character: Character) {
        viewModelScope.launch {
            try {
                toggleFavoriteUseCase(character.id).getOrThrow()
            } catch (ex: Throwable) {
                Timber.d("Could not toggle favorite for character. ex: $ex")
            }
        }
    }
}
