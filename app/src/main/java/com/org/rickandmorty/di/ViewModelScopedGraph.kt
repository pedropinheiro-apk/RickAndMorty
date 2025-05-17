package com.org.rickandmorty.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner

object ViewModelScopedGraph {
    private val graph = mutableMapOf<String, Any>()

    fun init(
        lifecycle: ViewModelStoreOwner,
        block: MutableMap<*, *>.() -> Unit
    ) {
        block(graph)
        lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        graph.clear()
        super.onDestroy(owner)
    }

    fun <T : Any> get(key: String, default: T) = graph[key] as? T ?: default
    fun <T : Any> get(key: String, default: (() -> T)) = graph[key] as? T ?: default.invoke()
}