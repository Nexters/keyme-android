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
import com.keyme.presentation.myprofile.ui.MyProfileDestination
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.signin.SignInDestination
import com.keyme.presentation.signin.SignInViewModel
import com.keyme.presentation.signin.enums.SignInStateEnum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun rememberKeymeAppState(
    signInViewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
): KeymeAppState {
    val coroutineScope = rememberCoroutineScope()
    return remember(navController) {
        KeymeAppState(coroutineScope, navController, signInViewModel)
    }
}

@Stable
class KeymeAppState(
    private val coroutineScope: CoroutineScope,
    val navController: NavHostController,
    private val signInViewModel: SignInViewModel,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination = SignInDestination

    var isSignIn by mutableStateOf(false)

    init {
        coroutineScope.launch {
            signInViewModel.keymeSignInState.collectLatest {
                isSignIn = it == SignInStateEnum.MY_DAILY
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
}
