package com.gramavasathi.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = WarmOrange,
    secondary = EarthyBrown,
    tertiary = DeepTerracotta,
    background = SoftCream,
    surface = WarmSand,
    onPrimary = SoftCream,
    onSecondary = SoftCream,
    onTertiary = SoftCream,
    onBackground = EarthyBrown,
    onSurface = EarthyBrown
)

private val DarkColorScheme = darkColorScheme(
    primary = WarmOrange,
    secondary = MutedPeach,
    tertiary = LeafGreen,
    background = EarthyBrown,
    surface = DeepTerracotta,
    onPrimary = EarthyBrown,
    onSecondary = EarthyBrown,
    onTertiary = SoftCream,
    onBackground = SoftCream,
    onSurface = SoftCream
)

@Composable
fun GramaVasathiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
