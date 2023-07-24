package com.keyme.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keyme.app.navigation.TopLevelDestination
import com.keyme.presentation.feed.ui.FeedDestination
import com.keyme.presentation.navigation.KeymeNavigationDestination

@Composable
fun rememberKeymeAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { KeymeAppState(navController) }

@Stable
class KeymeAppState(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = FeedDestination

    fun navigate(destination: KeymeNavigationDestination) {
        if (destination is TopLevelDestination) {
            navController.navigate(destination.route) {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(destination.route)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}
