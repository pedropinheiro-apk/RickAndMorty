package com.org.rickandmorty.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.org.rickandmorty.navigation.Routes

fun <T : Routes> SnapshotStateList<T>.dropLastRouteWhile(predicate: (T) -> Boolean) {
    while (isNotEmpty() && predicate(last())) {
        removeAt(lastIndex)
    }
}
