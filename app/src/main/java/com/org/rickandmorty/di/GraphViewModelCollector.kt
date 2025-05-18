package com.org.rickandmorty.di

import java.io.Closeable

class GraphViewModelCollector : Closeable {

    private val scopedKeys = mutableSetOf<String>()

    fun addKey(key: String) = scopedKeys.add(key)
    fun addKeys(vararg keys: String) = scopedKeys.addAll(keys)

    override fun close() {
        Graph.remove(*scopedKeys.toTypedArray())
        scopedKeys.clear()
    }
}
