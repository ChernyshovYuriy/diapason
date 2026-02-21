package com.yuriy.diapason.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.GraphicEq
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    // Bottom nav destinations
    object Analyze : Screen("analyze")
    object Guide : Screen("guide")
    object VoiceTypes : Screen("voice_types")

    // Full-screen destinations (pushed on top)
    object Results : Screen("results")
}

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(
        screen = Screen.Analyze,
        label = "Analyze",
        selectedIcon = Icons.Filled.GraphicEq,
        unselectedIcon = Icons.Outlined.GraphicEq
    ),
    BottomNavItem(
        screen = Screen.Guide,
        label = "Guide",
        selectedIcon = Icons.Filled.MenuBook,
        unselectedIcon = Icons.Outlined.MenuBook
    ),
    BottomNavItem(
        screen = Screen.VoiceTypes,
        label = "Voice Types",
        selectedIcon = Icons.Filled.LibraryMusic,
        unselectedIcon = Icons.Outlined.LibraryMusic
    )
)
