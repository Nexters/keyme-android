package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.keyme.domain.entity.response.Category
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.myprofile.KeymeQuestionResultViewModel
import com.keyme.presentation.navigation.KeymeNavigationDestination

object KeymeQuestionResultDestination : KeymeNavigationDestination {
    override val route: String = "keyme_test_result_detail_route"
    override val destination: String = "keyme_test_result_detail_destination"

    object Argument {
        val questionIdName: String = "questionId"
    }
}

fun NavGraphBuilder.keymeQuestionResultGraph(onBackClick: () -> Unit) {
    composable(
        route = "${KeymeQuestionResultDestination.route}/{${KeymeQuestionResultDestination.Argument.questionIdName}}",
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
    onBackClick: () -> Unit,
) {
    val statisticsState by keymeQuestionResultViewModel.statisticsState.collectAsStateWithLifecycle()
    val myScore by keymeQuestionResultViewModel.myScoreState.collectAsStateWithLifecycle()

    KeymeQuestionResultScreen(
        statistics = QuestionStatistic(
            avgScore = 4f,
            category = Category(
                color = "FFFFFF",
                iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/test_star.png",
                name = "의사_표현",
            ),
            title = "자신의 의견을 확고하게 말하시나요?",
            keyword = "의견",
            questionId = 0,
        ),
        onBackClick = onBackClick,
    )
}
