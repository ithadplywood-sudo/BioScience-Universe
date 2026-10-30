package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BioScienceDarkColorScheme = darkColorScheme(
    primary = CyanPrimary,
    onPrimary = DeepSpaceDark,
    primaryContainer = SpaceSurfaceVariant,
    onPrimaryContainer = CyanPrimary,
    secondary = ElectricBlue,
    onSecondary = DeepSpaceDark,
    secondaryContainer = SpaceSurfaceVariant,
    onSecondaryContainer = ElectricBlue,
    tertiary = BioEmerald,
    onTertiary = DeepSpaceDark,
    tertiaryContainer = SpaceSurfaceCard,
    onTertiaryContainer = BioMint,
    background = DeepSpaceDark,
    onBackground = TextWhite,
    surface = SpaceSurface,
    onSurface = TextWhite,
    surfaceVariant = SpaceSurfaceVariant,
    onSurfaceVariant = TextSlate,
    outline = SpaceBorder,
    outlineVariant = SpaceBorderGlow,
    error = BioRose,
    onError = Color.White
)

@Composable
fun BioScienceTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BioScienceDarkColorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    BioScienceTheme(darkTheme = darkTheme, content = content)
}
