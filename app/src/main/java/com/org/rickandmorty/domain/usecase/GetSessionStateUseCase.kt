package com.org.rickandmorty.domain.usecase

import com.org.rickandmorty.domain.model.SessionState

fun interface GetSessionStateUseCase : suspend () -> Result<SessionState>
