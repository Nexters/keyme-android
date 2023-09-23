package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.domain.entity.response.QuestionStatistic
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
    val myCharacter by myProfileViewModel.myCharacterState.collectAsStateWithLifecycle()
    val myProfileUiState by myProfileViewModel.myProfileUiState.collectAsStateWithLifecycle()
    val mySimilarStatistics by myProfileViewModel.mySimilarStatisticsState.collectAsStateWithLifecycle()
    val myDifferentStatistics by myProfileViewModel.myDifferentStatisticsState.collectAsStateWithLifecycle()

    MyProfileScreen(
        myProfileUiState = myProfileUiState,
        myCharacter = myCharacter,
        mySimilarStatistics = mySimilarStatistics,
        myDifferentStatistics = myDifferentStatistics,
        onInfoClick = myProfileViewModel::showToolTip,
        onSettingClick = navigateToSetting,
        onToolTipCloseClick = myProfileViewModel::dismissToolTip,
        onQuestionClick = navigateToQuestionResult,
    )
}
