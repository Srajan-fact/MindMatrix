package com.example.superheroes30days.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary
)

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary
)

@Composable
fun SuperheroesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}