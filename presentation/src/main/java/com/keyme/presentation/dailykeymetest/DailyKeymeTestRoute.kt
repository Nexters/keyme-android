package com.keyme.presentation.dailykeymetest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

object DailyKeymeTestDestination : KeymeNavigationDestination {
    override val route = "daily_keyme_test_route"
    override val destination = "daily_keyme_test_destination"
}

fun NavGraphBuilder.dailyKeymeTestGraph(
    navigateToTakeKeymeTest: (testId: Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = DailyKeymeTestDestination.route,
        startDestination = DailyKeymeTestDestination.destination,
    ) {
        composable(route = DailyKeymeTestDestination.destination) {
            DailyKeymeTestRoute(navigateToTakeKeymeTest = navigateToTakeKeymeTest)
        }
        nestedGraphs()
    }
}

@Composable
fun DailyKeymeTestRoute(
    dailyKeymeTestViewModel: DailyKeymeTestViewModel = hiltViewModel(),
    navigateToTakeKeymeTest: (testId: Int) -> Unit,
) {
    val myCharacter by dailyKeymeTestViewModel.myCharacterState.collectAsStateWithLifecycle()
    val dailyKeymeTest by dailyKeymeTestViewModel.dailyKeymeTestState.collectAsStateWithLifecycle()
    val dailyKeymeTestStatistic by dailyKeymeTestViewModel.dailyKeymeTestStatisticState.collectAsStateWithLifecycle()

    DailyKeymeTestScreen(
        myCharacter = myCharacter,
        dailyKeymeTest = dailyKeymeTest,
        dailyKeymeTestStatistic = dailyKeymeTestStatistic,
        onDailyKeymeTestClick = {
            navigateToTakeKeymeTest(dailyKeymeTest.testId)
        },
    )
}
