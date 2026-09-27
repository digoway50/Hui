package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val FleexColorScheme = lightColorScheme(
    primary = FleexPine,
    onPrimary = Color.White,
    primaryContainer = FleexPineDark,
    onPrimaryContainer = FleexCream,
    secondary = FleexSage,
    onSecondary = Color.White,
    secondaryContainer = FleexCream,
    onSecondaryContainer = FleexPine,
    tertiary = FleexGold,
    onTertiary = Color.White,
    tertiaryContainer = FleexCreamLight,
    onTertiaryContainer = FleexGoldDark,
    background = FleexBackgroundLight,
    onBackground = FleexTextPrimary,
    surface = FleexSurfaceLight,
    onSurface = FleexTextPrimary,
    surfaceVariant = FleexSurfaceVariant,
    onSurfaceVariant = FleexTextSecondary,
    outline = FleexBorder,
    outlineVariant = FleexCreamDark
)

private val FleexDarkColorScheme = darkColorScheme(
    primary = FleexCream,
    onPrimary = FleexPineDark,
    primaryContainer = FleexPine,
    onPrimaryContainer = FleexCream,
    secondary = FleexSageLight,
    onSecondary = FleexPineDark,
    secondaryContainer = FleexSageDark,
    onSecondaryContainer = FleexCreamLight,
    tertiary = FleexGoldLight,
    onTertiary = FleexPineDark,
    background = FleexPineDark,
    onBackground = FleexCreamLight,
    surface = FleexPine,
    onSurface = FleexCreamLight,
    surfaceVariant = FleexSageDark,
    onSurfaceVariant = FleexCream,
    outline = FleexSage,
    outlineVariant = FleexPine
)

@Composable
fun FleexGarmentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) FleexDarkColorScheme else FleexColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.let {
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(it, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
