package com.keyme.presentation.nickname

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

object NicknameDestination : KeymeNavigationDestination {
    override val route = "nickname_route"
    override val destination = "nickname_destination"
}

fun NavGraphBuilder.nicknameGraph(
    onBackClick: () -> Unit,
//    navigateToKeymeTest: () -> Unit,
) {
    navigation(
        route = NicknameDestination.route,
        startDestination = NicknameDestination.destination,
    ) {
        composable(route = NicknameDestination.destination) {
            NicknameRoute(
                onBackClick = onBackClick,
//                navigateToKeymeTest = navigateToKeymeTest,
            )
        }
    }
}
