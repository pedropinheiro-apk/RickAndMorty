package com.org.rickandmorty.app

import com.org.rickandmorty.data.repository.AlwaysAuthenticatedAuthRepositoryImpl
import com.org.rickandmorty.di.Graph

class SingletonInstancesInitializer {

    fun init() {
        Graph.putAll(
            mapOf(
                "auth_repository" to AlwaysAuthenticatedAuthRepositoryImpl(),
            ),
        )
    }
}
