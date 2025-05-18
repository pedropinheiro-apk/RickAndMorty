package com.org.rickandmorty.di

enum class Scope {
    Activity,
    Singleton;

    fun isLifecycleAware() = this == Activity
}
