package com.org.rickandmorty.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.org.rickandmorty.domain.usecase.GetCharactersPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCharactersPagerUseCase: GetCharactersPagerUseCase
) : ViewModel() {

    val charactersPager = getCharactersPagerUseCase()
        .cachedIn(viewModelScope)
}
