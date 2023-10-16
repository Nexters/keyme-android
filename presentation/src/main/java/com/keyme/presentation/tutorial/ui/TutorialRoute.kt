package com.keyme.presentation.tutorial.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val showTutorial by tutorialViewModel.showTutorial.collectAsStateWithLifecycle()
    if (!showTutorial) onBackClick()

    TutorialScreen(
        onStartClick = {
            tutorialViewModel.setLearned(true)
        },
    )
}
