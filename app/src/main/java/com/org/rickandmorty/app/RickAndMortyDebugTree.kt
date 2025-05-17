package com.org.rickandmorty.app

import timber.log.Timber

val RickAndMortyDebugTree: Timber.Tree by lazy {
    object : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String {
            return "RickAndMorty: ${super.createStackElementTag(element)}"
        }
    }
}
