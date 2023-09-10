package com.keyme.presentation.dailykeymetest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
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

    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    DailyKeymeTestScreen(
        myCharacter = myCharacter,
        dailyKeymeTest = dailyKeymeTest,
        dailyKeymeTestStatistic = dailyKeymeTestStatistic,
        onDailyKeymeTestClick = {
            navigateToTakeKeymeTest(dailyKeymeTest.testId)
        },
        onShareClick = {
            // todo 코드 정리 필요
            val testLink = "https://keyme-frontend.vercel.app/test/${dailyKeymeTest.testId}"
            clipboardManager.setText(AnnotatedString(testLink))
            // todo api level 분기 필요
//            Toast.makeText(context, "링크 복사 완료", Toast.LENGTH_SHORT).show()
        },
    )
}
