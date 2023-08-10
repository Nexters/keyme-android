package com.keyme.presentation.signin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

object SignInDestination : KeymeNavigationDestination {
    override val route = "signIn_route"
    override val destination = "signIn_destination"
}

fun NavGraphBuilder.signInGraph(
    navigateToNickname: () -> Unit,
//    navigateToHome: () -> Unit,
) {
    navigation(
        route = SignInDestination.route,
        startDestination = SignInDestination.destination,
    ) {
        composable(route = SignInDestination.destination) { backStackEntry ->
            SignInRoute(
                navigateToNickname = navigateToNickname,
//                navigateToHome = navigateToHome,
            )
        }
    }
}
