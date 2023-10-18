package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.KeymeBackgroundAnim
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.myprofile.MyProfileViewModel
import com.keyme.presentation.navigation.KeymeNavigationDestination

object MyProfileDestination : KeymeNavigationDestination {
    override val route: String = "myProfile_route"
    override val destination: String = "myProfile_destination"
}

fun NavGraphBuilder.myProfileGraph(
    navigateToQuestionResult: (QuestionStatistic) -> Unit,
    navigateToSetting: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = MyProfileDestination.route,
        startDestination = MyProfileDestination.destination,
    ) {
        composable(route = MyProfileDestination.destination) {
            MyProfileRoute(
                navigateToQuestionResult = navigateToQuestionResult,
                navigateToSetting = navigateToSetting,
            )
        }
        nestedGraphs()
    }
}

@Composable
fun MyProfileRoute(
    myProfileViewModel: MyProfileViewModel = hiltViewModel(),
    navigateToQuestionResult: (QuestionStatistic) -> Unit,
    navigateToSetting: () -> Unit,
) {
    val myProfileUiState by myProfileViewModel.myProfileUiState.collectAsStateWithLifecycle()

    KeymeBackgroundAnim()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = black_alpha_80),
    ) {
        MyProfileScreen(
            myProfileUiState = myProfileUiState,
            onSettingClick = navigateToSetting,
            onQuestionClick = navigateToQuestionResult,
        )
    }
}
