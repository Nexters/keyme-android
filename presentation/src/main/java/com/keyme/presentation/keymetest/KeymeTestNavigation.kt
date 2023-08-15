package com.keyme.presentation.keymetest

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeTestDestination : KeymeNavigationDestination {
    override val route = "keymeTest_route"
    override val destination = "keymeTest_destination"
}

fun NavGraphBuilder.keymeTestGraph(
    onBackClick: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    navigation(
        route = KeymeTestDestination.route,
        startDestination = KeymeTestDestination.destination,
    ) {
        composable(route = KeymeTestDestination.destination) {
            KeymeTestRoute(
                onBackClick = onBackClick,
                navigateToMyDaily = navigateToMyDaily
            )
        }
    }
}
