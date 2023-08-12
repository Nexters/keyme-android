package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeTestResultDetailDestination : KeymeNavigationDestination {
    override val route: String = "keyme_test_result_detail_route"
    override val destination: String = "keyme_test_result_detail_destination"
}

fun NavGraphBuilder.keymeTestResultDetailGraph(onBackClick: () -> Unit) {
    composable(route = KeymeTestResultDetailDestination.route) {
        KeymeTestResultDetailRoute(onBackClick = onBackClick)
    }
}

@Composable
fun KeymeTestResultDetailRoute(onBackClick: () -> Unit) {
    KeymeTestResultDetailScreen(onBackClick = onBackClick)
}
