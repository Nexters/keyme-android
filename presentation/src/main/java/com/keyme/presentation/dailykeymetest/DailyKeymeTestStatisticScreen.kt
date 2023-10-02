package com.keyme.presentation.dailykeymetest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.domain.entity.response.TestStatistic
import com.keyme.presentation.KeymeBackgroundAnim
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.utils.ColorUtil
import com.keyme.presentation.utils.clickableRippleEffect
import com.keyme.presentation.utils.toKeymeScore

private val bottomGradientHeightDp = 125.dp

@Composable
fun DailyKeymeTestStatisticScreen(
    myCharacter: Member,
    dailyKeymeTestStatistic: TestStatistic,
    onShareClick: () -> Unit,
    onQuestionStatisticClick: (QuestionStatistic) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            item {
                StatisticTextTitle()
            }
            item {
                StatisticSolvedCountInfo(dailyKeymeTestStatistic)
            }

            statisticList(
                myCharacter,
                dailyKeymeTestStatistic,
                onQuestionStatisticClick,
            )

            item { Spacer(modifier = Modifier.height(bottomGradientHeightDp)) }
        }

        ShareToFriendsButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f),
            onClick = onShareClick,
        )
    }
}

@Composable
private fun StatisticTextTitle(modifier: Modifier = Modifier) {
    KeymeText(
        modifier = modifier
            .padding(top = 75.dp, bottom = 24.dp)
            .fillMaxWidth(),
        text = "친구들의\n답변이 쌓이고 있어요!",
        keymeTextType = KeymeTextType.HEADING_1,
        color = Color(0xFFF8F8F8),
    )
}

@Composable
private fun StatisticSolvedCountInfo(dailyKeymeTestStatistic: TestStatistic) {
    Row(
        modifier = Modifier.padding(bottom = 33.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_member),
            contentDescription = "",
            tint = Color.White,
        )

        KeymeText(
            text = "${dailyKeymeTestStatistic.solvedCount}명의 친구가 문제를 풀었어요",
            keymeTextType = KeymeTextType.BODY_4,
            color = Color.White,
        )
    }
}

private fun LazyListScope.statisticList(
    myCharacter: Member,
    dailyKeymeTestStatistic: TestStatistic,
    onClickItem: (QuestionStatistic) -> Unit,
) {
    items(dailyKeymeTestStatistic.questionsStatistics) {
        QuestionStatisticItem(
            myCharacter = myCharacter,
            statistic = it,
            onClick = { onClickItem(it) },
        )
    }
}

@Composable
private fun QuestionStatisticItem(
    myCharacter: Member,
    statistic: QuestionStatistic,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color(0x0DFFFFFF), shape = RoundedCornerShape(size = 14.dp))
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .clickableRippleEffect { onClick() },
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = ColorUtil.hexStringToColor(statistic.category.color),
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                modifier = Modifier.size(20.dp),
                model = ImageRequest.Builder(LocalContext.current).data(statistic.category.iconUrl).build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            KeymeText(
                text = myCharacter.nickname + statistic.title,
                keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                color = Color.White,
            )

            KeymeText(
                text = "평균점수 | ${statistic.avgScore.toKeymeScore()}점",
                keymeTextType = KeymeTextType.BODY_4,
                color = Color(0x80FFFFFF),
            )
        }
    }
}

@Composable
private fun ShareToFriendsButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(bottomGradientHeightDp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, keyme_black),
                ),
            ),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 25.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            KeymeText(
                text = "테스트 공유하기",
                keymeTextType = KeymeTextType.BODY_2,
                color = Color.Black,
            )
        }
    }
}
