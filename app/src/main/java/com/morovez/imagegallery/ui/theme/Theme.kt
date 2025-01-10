package com.morovez.imagegallery.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Color.BackGroundDark,
    onPrimary = Color.PrimaryTextColorDark

)

private val LightColorScheme = lightColorScheme(
    background = Color.BackgroundLight,
    onPrimary = Color.PrimaryTextColorLight,
)

@Composable
fun ImageGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}