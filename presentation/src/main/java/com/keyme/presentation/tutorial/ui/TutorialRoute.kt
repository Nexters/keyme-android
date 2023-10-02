package com.keyme.presentation.tutorial.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.tutorial.TutorialViewModel

object TutorialDestination : KeymeNavigationDestination {
    override val route = "tutorial_route"
    override val destination = "tutorial_destination"
}

fun NavGraphBuilder.tutorialGraph(
    onBackClick: () -> Unit,
) {
    composable(route = TutorialDestination.route) {
        TutorialRoute(onBackClick = onBackClick)
    }
}


@Composable
fun TutorialRoute(
    tutorialViewModel: TutorialViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    TutorialScreen(
        onFinish = {
            tutorialViewModel.setLearned(true)
            onBackClick()
        },
    )
}
