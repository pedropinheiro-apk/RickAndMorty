package com.org.rickandmorty.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.rickandmorty.domain.usecase.GetSessionStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSessionState: GetSessionStateUseCase,
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val sessionState = getSessionState().getOrThrow()
                _isAuthenticated.value = sessionState.isAuthenticated()
            } catch (ex: Throwable) {
                Timber.e("Could not get session state: ${ex.message}")
            }
        }
    }
}
