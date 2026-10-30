package com.example.ui.responsive

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ScreenType {
    MOBILE,   // < 600dp (Smartphones)
    TABLET,   // 600dp .. 1023dp (Tablets, foldables)
    LAPTOP,   // 1024dp .. 1439dp (Laptops)
    DESKTOP   // >= 1440dp (Large monitors, 1920px+ displays)
}

enum class ViewportMode(val label: String, val iconText: String) {
    AUTO("Auto Detect", "🔄"),
    DESKTOP("Desktop (1920px)", "🖥️"),
    LAPTOP("Laptop (1280px)", "💻"),
    TABLET("Tablet (768px)", "📱"),
    MOBILE("Mobile (375px)", "📱")
}

data class ResponsiveConfig(
    val screenWidth: Dp,
    val screenType: ScreenType,
    val viewportMode: ViewportMode = ViewportMode.AUTO,
    val isSimulated: Boolean = false
) {
    val isMobile: Boolean get() = screenType == ScreenType.MOBILE
    val isTablet: Boolean get() = screenType == ScreenType.TABLET
    val isLaptop: Boolean get() = screenType == ScreenType.LAPTOP
    val isDesktop: Boolean get() = screenType == ScreenType.DESKTOP || screenType == ScreenType.LAPTOP
    val isWide: Boolean get() = screenType != ScreenType.MOBILE

    val contentMaxWidth: Dp
        get() = when (screenType) {
            ScreenType.MOBILE -> Dp.Unspecified
            ScreenType.TABLET -> Dp.Unspecified
            ScreenType.LAPTOP -> Dp.Unspecified
            ScreenType.DESKTOP -> Dp.Unspecified
        }
}

fun calculateResponsiveConfig(
    actualWidth: Dp,
    viewportMode: ViewportMode = ViewportMode.AUTO
): ResponsiveConfig {
    return when (viewportMode) {
        ViewportMode.DESKTOP -> ResponsiveConfig(
            screenWidth = 1920.dp,
            screenType = ScreenType.DESKTOP,
            viewportMode = viewportMode,
            isSimulated = actualWidth < 1440.dp
        )
        ViewportMode.LAPTOP -> ResponsiveConfig(
            screenWidth = 1280.dp,
            screenType = ScreenType.LAPTOP,
            viewportMode = viewportMode,
            isSimulated = actualWidth < 1024.dp
        )
        ViewportMode.TABLET -> ResponsiveConfig(
            screenWidth = 768.dp,
            screenType = ScreenType.TABLET,
            viewportMode = viewportMode,
            isSimulated = actualWidth < 600.dp
        )
        ViewportMode.MOBILE -> ResponsiveConfig(
            screenWidth = 375.dp,
            screenType = ScreenType.MOBILE,
            viewportMode = viewportMode,
            isSimulated = false
        )
        ViewportMode.AUTO -> {
            val type = when {
                actualWidth < 600.dp -> ScreenType.MOBILE
                actualWidth < 1024.dp -> ScreenType.TABLET
                actualWidth < 1440.dp -> ScreenType.LAPTOP
                else -> ScreenType.DESKTOP
            }
            ResponsiveConfig(
                screenWidth = actualWidth,
                screenType = type,
                viewportMode = viewportMode,
                isSimulated = false
            )
        }
    }
}
