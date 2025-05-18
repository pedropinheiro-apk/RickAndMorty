package com.org.rickandmorty.di

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

object GraphLifecycleCollector : DefaultLifecycleObserver {

    private var scopedKeys = mutableSetOf<String>()

    fun init(lifecycle: Lifecycle) = lifecycle.addObserver(this)

    fun addActivityScopedKey(key: String) = scopedKeys.add(key)

    fun addActivityScopedKey(vararg key: String) = scopedKeys.addAll(key)

    override fun onDestroy(owner: LifecycleOwner) {
        Graph.remove(*scopedKeys.toTypedArray())
        scopedKeys.clear()
        owner.lifecycle.removeObserver(this)
        super.onDestroy(owner)
    }
}
