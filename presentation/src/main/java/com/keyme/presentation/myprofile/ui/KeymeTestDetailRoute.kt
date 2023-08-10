package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeTestDetailDestination : KeymeNavigationDestination {
    override val route: String = "keyme_test_detail_route"
    override val destination: String = "keyme_test_detail_destination"
}

fun NavGraphBuilder.keymeTestDetailGraph(onBackClick: () -> Unit) {
    composable(route = KeymeTestDetailDestination.route) {
        KeymeTestDetailRoute(onBackClick = onBackClick)
    }
}

@Composable
fun KeymeTestDetailRoute(onBackClick: () -> Unit) {
    KeymeTestDetailScreen(onBackClick = onBackClick)
}
