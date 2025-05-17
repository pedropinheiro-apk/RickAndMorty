@file:Suppress("UNCHECKED_CAST")

package com.org.rickandmorty.di

object GlobalScopedGraph {

    fun init(block: MutableMap<String, *>.() -> Unit) = Graph.initChildGraph(block)

    fun <T : Any> get(key: String, default: T) = Graph.get(key) { default }
}
