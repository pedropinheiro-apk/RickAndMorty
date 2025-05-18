@file:Suppress("UNCHECKED_CAST")

package com.org.rickandmorty.di

import java.util.concurrent.ConcurrentHashMap

object Graph {
    private val graph = ConcurrentHashMap<String, Any>()

    fun <T> get(key: String) = graph[key] as? T

    fun remove(vararg keys: String) = keys.forEach { graph.remove(it) }

    fun <T : Any> put(key: String, value: T, scope: Scope = Scope.Singleton) {
        graph[key] = value

        if (scope.isLifecycleAware()) {
            GraphLifecycleCollector.addActivityScopedKey(key)
        }
    }

    fun putAll(map: Map<String, Any>, scope: Scope = Scope.Singleton) {
        map.forEach { (key, value) ->
            put(key, value, scope)
        }

        if (scope.isLifecycleAware()) {
            GraphLifecycleCollector.addActivityScopedKey(*map.keys.toTypedArray())
        }
    }
}
