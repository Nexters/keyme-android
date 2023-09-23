package com.keyme.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keyme.app.navigation.TopLevelDestination
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.onboarding.OnboardingDestination
import com.keyme.presentation.takekeymetest.TakeKeymeTestDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun rememberKeymeAppState(
    keymeAppStateViewModel: KeymeAppStateViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
): KeymeAppState {
    val coroutineScope = rememberCoroutineScope()
    return remember(navController) {
        KeymeAppState(coroutineScope, navController, keymeAppStateViewModel)
    }
}

@Stable
class KeymeAppState(
    private val coroutineScope: CoroutineScope,
    val navController: NavHostController,
    private val viewModel: KeymeAppStateViewModel,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    var startDestination by mutableStateOf<KeymeNavigationDestination>(OnboardingDestination)

    val showBottomBar: Boolean
        @Composable get() = currentDestination?.route.showBottomBar()

    init {
        coroutineScope.launch {
            viewModel.myMemberInfoState.collectLatest {
                if (it == null) startDestination = OnboardingDestination
            }
        }
    }

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

    fun navigate(destination: KeymeNavigationDestination, args: Any) {
        if (destination is TopLevelDestination) {
            navController.navigate("${destination.route}/$args") {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate("${destination.route}/$args")
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }

    private fun String?.showBottomBar(): Boolean {
        if (this == null) return false
        if (this.contains(OnboardingDestination.destination)) return false
        if (this.contains(TakeKeymeTestDestination.route)) return false

        return true
    }
}
