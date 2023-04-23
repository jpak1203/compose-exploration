package com.jpakku.composeexploration.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = SecondaryColor,
    onPrimary = SecondaryText,
    onSecondary = DarkSecondaryText,
    outline = Divider,
    error = AccentColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    secondary = SecondaryColor,
    onPrimary = PrimaryText,
    onSecondary = SecondaryText,
    outline = Divider,
    error = AccentColor
)

@Composable
fun ComposeExplorationTheme(
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
        shapes = Shapes,
        content = content
    )
}