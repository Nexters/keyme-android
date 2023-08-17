package com.keyme.presentation.takekeymetest

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import com.keyme.presentation.navigation.KeymeNavigationDestination

object TakeKeymeTestDestination : KeymeNavigationDestination {
    override val route = "take_keyme_test_route"
    override val destination = "take_keyme_test_destination"

    object Argument {
        val testIdName: String = "testId"
    }
}

fun NavGraphBuilder.takeKeymeTestGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = "${TakeKeymeTestDestination.route}/${TakeKeymeTestDestination.Argument.testIdName}",
        arguments = listOf(
            navArgument(KeymeQuestionResultDestination.Argument.questionIdName) {
                type = NavType.IntType
            },
        ),
    ) {
        TakeKeymeTestRoute(onBackClick)
    }
}

@Composable
fun TakeKeymeTestRoute(
    onBackClick: () -> Unit,
) {

}
