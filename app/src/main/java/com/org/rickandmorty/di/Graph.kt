@file:Suppress("UNCHECKED_CAST")

package com.org.rickandmorty.di

import java.util.concurrent.ConcurrentHashMap

object Graph {
    private val graph = ConcurrentHashMap<String, Any>()

    fun initChildGraph(block: MutableMap<String, *>.() -> Unit) = block(graph)

    fun <T : Any> get(key: String, default: () -> T) = graph.getOrPut(key) { default() } as T

    fun remove(vararg keys: String) = keys.forEach { graph.remove(it) }

    fun <T : Any> put(key: String, value: T) {
        graph[key] = value
    }
}
