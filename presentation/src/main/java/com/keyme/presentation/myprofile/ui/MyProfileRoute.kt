package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.myprofile.MyProfileViewModel
import com.keyme.presentation.navigation.KeymeNavigationDestination

object MyProfileDestination : KeymeNavigationDestination {
    override val route: String = "myProfile_route"
    override val destination: String = "myProfile_destination"
}

fun NavGraphBuilder.myProfileGraph(
    navigateToQuestionResult: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    composable(route = MyProfileDestination.route) {
        MyProfileRoute(navigateToQuestionResult = navigateToQuestionResult)
    }
    nestedGraphs()
}

@Composable
fun MyProfileRoute(
    myProfileViewModel: MyProfileViewModel = hiltViewModel(),
    navigateToQuestionResult: () -> Unit,
) {
    val resultCircle by myProfileViewModel.resultCircleState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        KeymeTestStatisticsScreen(
            circles = resultCircle,
            onTestItemClick = { navigateToQuestionResult() },
        )
    }
}
