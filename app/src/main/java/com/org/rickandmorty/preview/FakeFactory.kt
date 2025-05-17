package com.org.rickandmorty.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode

object FakeFactory

@Composable
fun checkIsInPreviewScope(): Nothing? {
    if (!LocalInspectionMode.current) {
        error("The field you are trying to access should not be called outside preview scope")
    }

    return null
}
