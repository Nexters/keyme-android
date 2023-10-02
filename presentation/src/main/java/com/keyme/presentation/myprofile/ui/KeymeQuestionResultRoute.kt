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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.keyme.presentation.KeymeBackgroundAnim
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.myprofile.KeymeQuestionResultViewModel
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeQuestionResultDestination : KeymeNavigationDestination {
    const val questionIdArg = "questionId"
    override val route: String = "keyme_test_result_detail_route"
    override val destination: String = "keyme_test_result_detail_destination"
}

fun NavGraphBuilder.keymeQuestionResultGraph(onBackClick: () -> Unit) {
    composable(
        route = "${KeymeQuestionResultDestination.route}/{${KeymeQuestionResultDestination.questionIdArg}}",
        arguments = listOf(
            navArgument(KeymeQuestionResultDestination.questionIdArg) {
                type = NavType.StringType
            },
        ),
    ) {
        KeymeQuestionResultRoute(onBackClick = onBackClick)
    }
}

@Composable
fun KeymeQuestionResultRoute(
    keymeQuestionResultViewModel: KeymeQuestionResultViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val statisticsState by keymeQuestionResultViewModel.statisticsState.collectAsStateWithLifecycle()
    val myCharacter by keymeQuestionResultViewModel.myCharacterState.collectAsStateWithLifecycle()
    val solvedScorePagingItem = keymeQuestionResultViewModel.solvedScorePagingFlow.collectAsLazyPagingItems()

    KeymeBackgroundAnim()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = black_alpha_80),
    ) {
        KeymeQuestionResultScreen(
            myCharacter = myCharacter,
            statistics = statisticsState,
            solvedScorePagingItem = solvedScorePagingItem,
            onBackClick = onBackClick,
        )
    }
}
