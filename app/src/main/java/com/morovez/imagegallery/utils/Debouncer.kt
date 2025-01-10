package com.morovez.imagegallery.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.throttleClickable(
    interval: Long = 1000L,
    crossinline onClick: () -> Unit,
): Modifier = composed {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    clickable {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) < interval) return@clickable
        lastClickTime = currentTime
        onClick()
    }
}