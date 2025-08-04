package com.org.rickandmorty.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable

val PaletteCollectionSaver = mapSaver(
    save = { collection ->
        mapOf(
            "txt" to collection.textPalettesInternal,
            "bg" to collection.backgroundPalettesInternal,
        )
    },
    restore = { map ->
        PaletteCollection().apply {
            setTextFromMap(map["txt"] as? Map<Long, Int> ?: emptyMap())
            setBackgroundFromMap(map["bg"] as? Map<Long, Int> ?: emptyMap())
        }
    }
)

@Composable
fun rememberPaletteCollection(): PaletteCollection {
    return rememberSaveable(saver = PaletteCollectionSaver) { PaletteCollection() }
}

@Stable
class PaletteCollection {
    /**
     * This is a workaround to ensure that palettes are not lost when the
     * app is recreated (e.g., due to a configuration change).
     */
    internal val textPalettesInternal = mutableMapOf<Long, Int>()
    internal val backgroundPalettesInternal = mutableMapOf<Long, Int>()

    val textPalettes = mutableStateMapOf<Long, Int>()
    val backgroundPalettes = mutableStateMapOf<Long, Int>()

    fun getBackgroundPalette(key: Long): Int? = backgroundPalettes[key]

    fun setBackgroundPalette(key: Long, color: Int) {
        backgroundPalettesInternal[key] = color
        backgroundPalettes[key] = color
    }

    fun getTextPalette(key: Long): Int? = textPalettes[key]

    fun setTextPalette(key: Long, color: Int) {
        textPalettesInternal[key] = color
        textPalettes[key] = color
    }

    fun setBackgroundFromMap(map: Map<Long, Int>) {
        backgroundPalettesInternal.putAll(map)
        backgroundPalettes.putAll(map)
    }

    fun setTextFromMap(map: Map<Long, Int>) {
        textPalettesInternal.putAll(map)
        textPalettes.putAll(map)
    }
}
