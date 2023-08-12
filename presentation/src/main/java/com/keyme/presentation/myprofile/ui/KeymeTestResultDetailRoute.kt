package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeTestResultDetailDestination : KeymeNavigationDestination {
    override val route: String = "keyme_test_result_detail_route/{${Argument.questionIdName}}"
    override val destination: String = "keyme_test_result_detail_destination"

    object Argument {
        val questionIdName: String = "questionId"
    }
}

fun NavGraphBuilder.keymeTestResultDetailGraph(onBackClick: () -> Unit) {
    composable(
        route = KeymeTestResultDetailDestination.route,
        arguments = listOf(
            navArgument(KeymeTestResultDetailDestination.Argument.questionIdName) {
                type = NavType.StringType
            },
        ),
    ) {
        KeymeTestResultDetailRoute(onBackClick = onBackClick)
    }
}

@Composable
fun KeymeTestResultDetailRoute(onBackClick: () -> Unit) {
    KeymeTestResultDetailScreen(onBackClick = onBackClick)
}
