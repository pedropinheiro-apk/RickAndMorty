package com.org.rickandmorty.domain.model

enum class SessionState {
    Authenticated,
    Unauthenticated;

    fun isAuthenticated() = this == Authenticated
}
