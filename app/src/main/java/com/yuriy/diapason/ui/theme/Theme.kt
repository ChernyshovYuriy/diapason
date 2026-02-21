package com.yuriy.diapason.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Fallback dark scheme (API < 31 — no dynamic color)
private val FallbackDarkColorScheme = darkColorScheme(
    primary = DiapasonPrimary,
    onPrimary = DiapasonOnPrimary,
    primaryContainer = DiapasonPrimaryContainer,
    onPrimaryContainer = DiapasonOnPrimaryContainer,
    secondary = DiapasonSecondary,
    onSecondary = DiapasonOnSecondary,
    secondaryContainer = DiapasonSecondaryContainer,
    onSecondaryContainer = DiapasonOnSecondaryContainer,
    tertiary = DiapasonTertiary,
    surface = DiapasonSurface,
    onSurface = DiapasonOnSurface,
    surfaceVariant = DiapasonSurfaceVariant,
    onSurfaceVariant = DiapasonOnSurfaceVariant,
    background = DiapasonBackground,
    onBackground = DiapasonOnBackground,
    error = DiapasonError,
    onError = DiapasonOnError,
    outline = DiapasonOutline
)

// Fallback light scheme (API < 31)
private val FallbackLightColorScheme = lightColorScheme(
    primary = Color(0xFF5B4ECC),
    onPrimary = DiapasonOnPrimary,
    primaryContainer = Color(0xFFE6E1FF),
    onPrimaryContainer = Color(0xFF1A0E6A),
    secondary = Color(0xFF5C5390),
    onSecondary = DiapasonOnPrimary,
    secondaryContainer = Color(0xFFE4DFFF),
    onSecondaryContainer = Color(0xFF19104A),
    surface = Color(0xFFFBF8FF),
    onSurface = Color(0xFF1B1B21),
    background = Color(0xFFFBF8FF),
    onBackground = Color(0xFF1B1B21)
)

@Composable
fun DiapasonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Material You — API 31+
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> FallbackDarkColorScheme
        else -> FallbackLightColorScheme
    }

    // Make status bar transparent and control icon color
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = DiapasonTypography,
        content = content
    )
}

// Convenience re-export so import of Color class from this package doesn't conflict
private fun Color(color: Long) = androidx.compose.ui.graphics.Color(color.toULong())
