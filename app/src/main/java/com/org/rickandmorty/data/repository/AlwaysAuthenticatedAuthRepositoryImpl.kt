package com.org.rickandmorty.data.repository

import com.org.rickandmorty.domain.model.SessionState
import com.org.rickandmorty.domain.repository.AuthRepository
import javax.inject.Inject

class AlwaysAuthenticatedAuthRepositoryImpl @Inject constructor() : AuthRepository {
    override suspend fun getSessionState() = Result.success(SessionState.Authenticated)
}
