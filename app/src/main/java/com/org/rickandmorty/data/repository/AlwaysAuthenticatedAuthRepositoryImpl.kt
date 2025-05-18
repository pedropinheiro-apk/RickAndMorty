package com.org.rickandmorty.data.repository

import com.org.rickandmorty.domain.model.SessionState
import com.org.rickandmorty.domain.repository.AuthRepository

class AlwaysAuthenticatedAuthRepositoryImpl : AuthRepository {
    override suspend fun getSessionState() = Result.success(SessionState.Authenticated)
}
