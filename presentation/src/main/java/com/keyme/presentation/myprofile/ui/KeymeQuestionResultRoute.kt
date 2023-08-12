package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.domain.entity.response.keymetest.Category
import com.keyme.domain.entity.response.keymetest.QuestionsStatistic
import com.keyme.presentation.myprofile.KeymeQuestionResultViewModel
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeQuestionResultDestination : KeymeNavigationDestination {
    override val route: String = "keyme_test_result_detail_route/{${Argument.questionIdName}}"
    override val destination: String = "keyme_test_result_detail_destination"

    object Argument {
        val questionIdName: String = "questionId"
    }
}

fun NavGraphBuilder.keymeQuestionResultGraph(onBackClick: () -> Unit) {
    composable(
        route = KeymeQuestionResultDestination.route,
        arguments = listOf(
            navArgument(KeymeQuestionResultDestination.Argument.questionIdName) {
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
    onBackClick: () -> Unit
) {
    val statisticsState by keymeQuestionResultViewModel.statisticsState.collectAsStateWithLifecycle()

    KeymeQuestionResultScreen(
        statistics = QuestionsStatistic(
            averageScore = 0,
            category = Category(
                color = "",
                imageUrl = "",
                name = ""
            ),
            description = "", keyword = "", questionId = 0
        ),
        onBackClick = onBackClick
    )
}
