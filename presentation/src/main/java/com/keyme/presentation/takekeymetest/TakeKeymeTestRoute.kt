package com.keyme.presentation.takekeymetest

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.presentation.navigation.KeymeNavigationDestination

object TakeKeymeTestDestination : KeymeNavigationDestination {
    const val testIdArg = "testId"
    override val route = "take_keyme_test_route"
    override val destination = "take_keyme_test_destination"
}

fun NavGraphBuilder.takeKeymeTestGraph(
    navigateToKeymeTestResult: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable(
        route = "${TakeKeymeTestDestination.route}/{${TakeKeymeTestDestination.testIdArg}}",
        arguments = listOf(
            navArgument(TakeKeymeTestDestination.testIdArg) {
                type = NavType.IntType
            },
        ),
    ) {
        TakeKeymeTestRoute(
            navigateToKeymeTestResult = navigateToKeymeTestResult,
            onBackClick = onBackClick,
            onCloseClick = { onBackClick() },
        )
    }
}

@Composable
fun TakeKeymeTestRoute(
    takeKeymeTestViewModel: TakeKeymeTestViewModel = hiltViewModel(),
    navigateToKeymeTestResult: () -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val loadTestUrl = takeKeymeTestViewModel.keymeTestUrl

    if (loadTestUrl.isNotEmpty()) {
        TakeKeymeTestScreen(
            loadTestUrl = loadTestUrl,
            onTestSolved = {
                navigateToKeymeTestResult()
            },
            onBackClick = { },
            onCloseClick = { },
        )
    } else {
        onCloseClick()
    }
}
