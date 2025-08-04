package com.org.rickandmorty.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.domain.usecase.GetCharactersPagerUseCase
import com.org.rickandmorty.domain.usecase.ToggleCharacterFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCharactersPagerUseCase: GetCharactersPagerUseCase,
    private val toggleFavoriteUseCase: ToggleCharacterFavoriteUseCase,
) : ViewModel() {

    val charactersPager = getCharactersPagerUseCase()
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
