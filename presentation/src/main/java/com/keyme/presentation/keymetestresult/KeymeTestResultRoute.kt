package com.keyme.presentation.keymetestresult

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.takekeymetest.TakeKeymeTestScreen
import com.keyme.presentation.takekeymetest.TakeKeymeTestViewModel

object KeymeTestResultDestination : KeymeNavigationDestination {
    const val testIdArg = "testId"
    override val route = "keyme_test_result_route"
    override val destination = "keyme_test_result_destination"
}

fun NavGraphBuilder.keymeTestResultGraph(
    onBackClick: () -> Unit,
) {
    composable(
        route = "${KeymeTestResultDestination.route}/{${KeymeTestResultDestination.testIdArg}}",
        arguments = listOf(
            navArgument(KeymeTestResultDestination.testIdArg) {
                type = NavType.IntType
            },
        ),
    ) {
        KeymeTestResultRoute(
            onBackClick = onBackClick,
            onCloseClick = { onBackClick() },
        )
    }
}

@Composable
fun KeymeTestResultRoute(
    takeKeymeTestViewModel: TakeKeymeTestViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val loadTestUrl = takeKeymeTestViewModel.keymeTestUrl
    var testResultId by remember { mutableStateOf(0) }
    val isTestSolved = testResultId != 0

    if (loadTestUrl.isNotEmpty()) {
        if (isTestSolved.not()) {
            TakeKeymeTestScreen(
                loadTestUrl = loadTestUrl,
                onTestSolved = {
                    testResultId = it
                },
                onBackClick = onBackClick,
                onCloseClick = onCloseClick,
            )
        } else {
            KeymeTestResultScreen(
                onCloseClick = {},
            )
        }
    } else {
        onBackClick()
    }
}
