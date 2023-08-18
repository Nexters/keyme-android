package com.keyme.presentation.takekeymetest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.presentation.keymetestresult.KeymeTestResultScreen
import com.keyme.presentation.navigation.KeymeNavigationDestination

object TakeKeymeTestDestination : KeymeNavigationDestination {
    const val testIdArg = "testId"
    override val route = "take_keyme_test_route"
    override val destination = "take_keyme_test_destination"
}

fun NavGraphBuilder.takeKeymeTestGraph(
    onBackClick: () -> Unit,
    onTestSolved: () -> Unit,
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
            onBackClick = onBackClick,
            onCloseClick = { onBackClick() },
            onTestSolved = { onTestSolved() }
        )
    }
}

@Composable
fun TakeKeymeTestRoute(
    takeKeymeTestViewModel: TakeKeymeTestViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onTestSolved: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val loadTestUrl = takeKeymeTestViewModel.keymeTestUrl
    val keymeTestResult by takeKeymeTestViewModel.keymeTestResultState.collectAsStateWithLifecycle()
    val myCharacter by takeKeymeTestViewModel.myCharacterState.collectAsStateWithLifecycle()

    if (loadTestUrl.isNotEmpty()) {
        if (keymeTestResult == null) {
            TakeKeymeTestScreen(
                loadTestUrl = loadTestUrl,
                onTestSolved = {
                    takeKeymeTestViewModel.updateTestResult(it)
                },
                onBackClick = onBackClick,
                onCloseClick = onCloseClick,
            )
        } else {
            KeymeTestResultScreen(
                myCharacter = myCharacter,
                testResult = keymeTestResult,
                onCloseClick = {
                    onTestSolved()
                },
            )
        }
    } else {
        onBackClick()
    }
}
