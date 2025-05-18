package com.org.rickandmorty.domain.repository

import com.org.rickandmorty.domain.model.SessionState

interface AuthRepository {
   suspend fun getSessionState(): Result<SessionState>
}
