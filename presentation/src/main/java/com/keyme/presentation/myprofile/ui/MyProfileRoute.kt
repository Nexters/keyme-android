package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.domain.entity.member.Member
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
    val myStatistics by myProfileViewModel.myStatisticsState.collectAsStateWithLifecycle()

    MyProfileScreen(
        myCharacter = Member(
            friendCode = "",
            id = 0,
            nickname = "Anonymous",
            profileImage = "",
            profileThumbnail = "",
        ),
        mySimilarStatistics = myStatistics,
        myDifferentStatistics = myStatistics,
        onQuestionClick = {
            navigateToQuestionResult()
        },
    )
}
