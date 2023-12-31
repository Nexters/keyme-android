package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Category
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.component.KeymeTitle
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.utils.textDp
import com.keyme.presentation.utils.toKeymeScore
import kotlinx.coroutines.flow.flowOf

@Composable
fun KeymeQuestionResultScreen(
    myCharacter: Member,
    statistics: QuestionStatistic,
    solvedScorePagingItem: LazyPagingItems<QuestionSolvedScore>,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        KeymeTitle(title = "", onBackClick = onBackClick)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var showMyScore by remember { mutableStateOf(false) }

            Spacer(modifier = Modifier.height(8.dp))

            KeymeQuestionStatisticsInfo(statistics, showMyScore)

            KeymeQuestionStatisticsCircle(
                statistics,
                onPressedUp = { showMyScore = true },
                onPressedDown = { showMyScore = false },
            )

            KeymeQuestionSolvedScoreList(myCharacter, statistics, solvedScorePagingItem)
        }
    }
}

@Composable
private fun KeymeQuestionStatisticsInfo(
    questionStatistic: QuestionStatistic,
    showMyScore: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KeymeText(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 16.dp),
                )
                .background(color = Color(0x33FFFFFF), shape = RoundedCornerShape(size = 16.dp))
                .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
            text = if (showMyScore) {
                "나의 점수"
            } else {
                questionStatistic.keyword
            },
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
            color = Color.White,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = if (showMyScore) {
                    questionStatistic.myScore.toString()
                } else {
                    questionStatistic.avgScore.toKeymeScore().toString()
                },
                fontFamily = panchang,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.textDp(),
                color = Color.White.copy(alpha = 0.6f),
            )
            KeymeText(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 10.dp),
                text = "점",
                keymeTextType = KeymeTextType.BODY_3_REGULAR,
                color = Color.White.copy(alpha = 0.6f),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun KeymeQuestionDetailScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = keyme_black),
    ) {
        KeymeQuestionResultScreen(
            myCharacter = Member.EMPTY,
            statistics = QuestionStatistic(
                avgScore = 3f,
                category = Category(
                    color = "FFFFFF",
                    iconUrl = "",
                    name = "",
                ),
                title = "",
                keyword = "",
                questionId = 0,
                myScore = 0,
            ),
            solvedScorePagingItem = flowOf(PagingData.empty<QuestionSolvedScore>()).collectAsLazyPagingItems(),
            onBackClick = {},
        )
    }
}
