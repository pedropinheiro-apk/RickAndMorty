@file:Suppress("UNCHECKED_CAST")

package com.org.rickandmorty.di

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

object ActivityScopedGraph : DefaultLifecycleObserver {

    private var scopedKeys = emptyList<String>()

    fun init(
        lifecycle: Lifecycle,
        keys: List<String> = emptyList(),
        block: MutableMap<String, *>.() -> Unit,
    ) {
        scopedKeys = keys
        Graph.initChildGraph(block)
        lifecycle.addObserver(this)
    }

    fun <T : Any> get(key: String, default: T) = Graph.get(key) { default }

    override fun onDestroy(owner: LifecycleOwner) {
        Graph.remove(*scopedKeys.toTypedArray())
        super.onDestroy(owner)
    }
}
