package com.keyme.presentation.takekeymetest

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Category
import com.keyme.domain.entity.response.QuestionResult
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.domain.entity.response.TestResult
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.myprofile.ui.KeymeQuestionStatisticsCircle
import com.keyme.presentation.utils.clickableRippleEffect
import com.keyme.presentation.utils.textDp

@Composable
fun KeymeTestResultScreen(
    myCharacter: Member,
    testResult: TestResult?,
    onCloseClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickableRippleEffect(bounded = false) {
                    onCloseClick()
                },
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "",
            tint = Color.White,
        )

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(51.dp))

            KeymeText(
                modifier = Modifier.padding(start = 16.dp),
                text = "결과 확인",
                keymeTextType = KeymeTextType.HEADING_1,
                color = Color(0xFFF8F8F8),
            )

            Spacer(modifier = Modifier.height(28.dp))

            testResult?.let {
                KeymeTestResultPager(myCharacter, testResult)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun KeymeTestResultPager(
    myCharacter: Member,
    testResult: TestResult,
) {
    Column {
        val pagerState = rememberPagerState(initialPage = 0)

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pageCount = testResult.results.count(),
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = 16.dp,
        ) {
            val item = testResult.results[it]
            KeymeTestResultItem(myCharacter = myCharacter, item = item)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = testResult.results.count(),
                activeColor = Color.White,
                inactiveColor = Color(0x4DFFFFFF),
                indicatorWidth = 6.dp,
                indicatorHeight = 6.dp,
            )
        }
    }
}

@Composable
fun KeymeTestResultItem(
    myCharacter: Member,
    item: QuestionResult,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF232323), shape = RoundedCornerShape(size = 24.dp))
            .border(width = 1.dp, color = Color(0xFF3A3A3A), shape = RoundedCornerShape(size = 24.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 33.dp, start = 32.dp, end = 32.dp),
        ) {
            KeymeText(
                text = item.category.name,
                keymeTextType = KeymeTextType.BODY_4,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            KeymeText(
                text = "${myCharacter.nickname}님의 ${item.keyword} 정도는?",
                keymeTextType = KeymeTextType.HEADING_1,
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0x1AFFFFFF))

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = item.score.toString(),
                    fontFamily = panchang,
                    fontSize = 32.textDp(),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0x99FFFFFF),
                )

                KeymeText(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(bottom = 4.dp),
                    text = "점",
                    keymeTextType = KeymeTextType.CAPTION_1,
                    color = Color(0x99FFFFFF),
                )
            }
        }

        Spacer(modifier = Modifier.height(17.dp))

        KeymeQuestionStatisticsCircle(
            statistics = item.toStatistic(),
            myScore = null,
            onPressedUp = { },
            onPressedDown = {},
        )
    }
}

fun QuestionResult.toStatistic() = QuestionStatistic(
    questionId = questionId,
    title = title,
    keyword = keyword,
    category = category,
    avgScore = score.toFloat(),
)

@Preview
@Composable
private fun KeymeTestResultScreenPreview() {
    val testResult = TestResult(
        matchRate = 0.0f,
        results = listOf(
            QuestionResult(
                category = Category(
                    color = "568049",
                    iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/money.png",
                    name = "MONEY",
                ),
                keyword = "keyword1",
                questionId = 0,
                score = 4,
                title = "",
            ),
            QuestionResult(
                category = Category(
                    color = "568049",
                    iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/money.png",
                    name = "MONEY",
                ),
                keyword = "keyword2",
                questionId = 0,
                score = 2,
                title = "",
            ),
            QuestionResult(
                category = Category(
                    color = "568049",
                    iconUrl = "https://keyme-ec2-access-s3.s3.ap-northeast-2.amazonaws.com/icon/money.png",
                    name = "MONEY",
                ),
                keyword = "keyword3",
                questionId = 0,
                score = 3,
                title = "",
            ),
        ),
        testId = 0, testResultId = 0,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = keyme_black),
    ) {
        KeymeTestResultScreen(
            myCharacter = Member(nickname = "keyme"),
            testResult = testResult,
            onCloseClick = {},
        )
    }
}
