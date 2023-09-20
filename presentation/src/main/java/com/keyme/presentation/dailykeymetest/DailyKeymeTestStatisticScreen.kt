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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.domain.entity.response.TestStatistic
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.utils.ColorUtil
import com.keyme.presentation.utils.clickableRippleEffect

@Composable
fun DailyKeymeTestStatisticScreen(
    myCharacter: Member,
    dailyKeymeTestStatistic: TestStatistic,
    onShareClick: () -> Unit,
    onQuestionStatisticClick: (QuestionStatistic) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StatisticTextTitle(myCharacter = myCharacter)
        Spacer(modifier = Modifier.height(96.dp))
        StatisticList(
            modifier = Modifier.weight(1f),
            myCharacter,
            dailyKeymeTestStatistic,
            onQuestionStatisticClick,
        )
        ShareToFriendsButton(onClick = onShareClick)
    }
}

@Composable
private fun StatisticTextTitle(modifier: Modifier = Modifier, myCharacter: Member) {
    KeymeText(
        modifier = modifier
            .padding(vertical = 75.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        text = "${myCharacter.nickname}님 친구들의 답변이 쌓이고 있어요!",
        keymeTextType = KeymeTextType.HEADING_1,
        color = Color(0xFFF8F8F8),
    )
}

@Composable
private fun StatisticList(
    modifier: Modifier = Modifier,
    myCharacter: Member,
    dailyKeymeTestStatistic: TestStatistic,
    onClickItem: (QuestionStatistic) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
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

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(dailyKeymeTestStatistic.questionsStatistics) {
                QuestionStatisticItem(
                    myCharacter = myCharacter,
                    statistic = it,
                    onClick = { onClickItem(it) },
                )
            }
        }
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
            .background(color = Color(0x0DFFFFFF), shape = RoundedCornerShape(size = 14.dp))
            .padding(horizontal = 14.dp, vertical = 20.dp)
            .clickableRippleEffect { onClick() },
        verticalAlignment = Alignment.CenterVertically,
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
                model = ImageRequest.Builder(LocalContext.current)
                    .data(statistic.category.iconUrl)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            KeymeText(
                text = "${myCharacter.nickname}님의 ${statistic.keyword} 정도는?",
                keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                color = Color.White,
            )

            KeymeText(
                text = "평균점수 | ${statistic.avgScore}점",
                keymeTextType = KeymeTextType.BODY_4,
                color = Color(0x80FFFFFF),
            )
        }
    }
}

@Composable
private fun ShareToFriendsButton(onClick: () -> Unit) {
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
            text = "친구에게 공유하기",
            keymeTextType = KeymeTextType.BODY_2,
            color = Color.Black,
        )
    }
}
