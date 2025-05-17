@file:Suppress("UNCHECKED_CAST")

package com.org.rickandmorty.di

object ComposableScopedGraph {
    private val graph = mutableMapOf<String, Any>()

    fun init(block: MutableMap<*, *>.() -> Unit) = block(graph)

    fun <T : Any> get(key: String, default: T) = graph[key] as? T ?: default
    fun <T : Any> get(key: String, default: (() -> T)) = graph[key] as? T ?: default.invoke()
}
