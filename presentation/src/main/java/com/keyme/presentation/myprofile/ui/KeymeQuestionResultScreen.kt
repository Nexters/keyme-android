package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.domain.entity.response.keymetest.Category
import com.keyme.domain.entity.response.keymetest.QuestionsStatistic
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.BottomSheetHandle
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.utils.ColorUtil
import com.keyme.presentation.utils.clickableRippleEffect
import com.keyme.presentation.utils.getUploadTimeString
import com.keyme.presentation.utils.textDp
import timber.log.Timber

@Composable
fun KeymeQuestionResultScreen(
    statistics: QuestionsStatistic,
    onBackClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp, start = 16.dp)
                .clickableRippleEffect(bounded = false) { onBackClick() },
            painter = painterResource(id = R.drawable.icon_nav_back),
            contentDescription = "",
            tint = Color.White,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KeymeQuestionStatisticsInfo(statistics)

            KeymeQuestionStatisticsCircle(statistics)

            KeymeQuestionScoreListContainer {
                KeymeQuestionInfo(title = "키미미미미미님의 애정 표현정도는?", solvedCount = 10)

                Spacer(modifier = Modifier.height(12.dp))

                Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0x1AFFFFFF))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(100) {
                        KeymeQuestionScoreItem("5", System.currentTimeMillis())
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.KeymeQuestionStatisticsCircle(statistics: QuestionsStatistic) {
    val density = LocalDensity.current
    var containerCircleSize by remember { mutableStateOf(IntSize.Zero) }
    val avgScoreCircleSize = DpSize(
        width = with(density) { ((containerCircleSize.width / 5) * statistics.averageScore).toDp() },
        height = with(density) { ((containerCircleSize.height / 5) * statistics.averageScore).toDp() },
    )

    Box(
        modifier = Modifier
            .padding(top = 12.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)
            .weight(1f)
            .aspectRatio(1f)
            .shadow(
                elevation = 20.dp,
                spotColor = Color(0x00000000),
                ambientColor = Color(0x00000000),
            )
            .border(
                width = 1.dp,
                color = Color(0x4DFFFFFF),
                shape = RoundedCornerShape(size = 320.00003.dp),
            )
            .background(color = Color(0x4DFFFFFF), shape = CircleShape)
            .clip(CircleShape)
            .onGloballyPositioned {
                containerCircleSize = it.size
            },
        contentAlignment = Alignment.Center,
    ) {

        Box(
            modifier = Modifier
                .size(avgScoreCircleSize)
                .background(
                    color = ColorUtil.hexStringToColor(statistics.category.color),
                    shape = CircleShape,
                )
                .clip(CircleShape),
        )

        AsyncImage(
            modifier = Modifier.size(48.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(statistics.category.imageUrl)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ColumnScope.KeymeQuestionScoreListContainer(content: @Composable ColumnScope.() -> Unit) {
    var bottomWeightValue by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF232323),
                RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            )
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .weight(bottomWeightValue),
    ) {
        BottomSheetHandle(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        bottomWeightValue = (bottomWeightValue - dragAmount / 200).coerceIn(1f, 5f)
                        Timber.d("bottomWeightValue: $bottomWeightValue")
                    }
                },
        )

        content()
    }
}

@Composable
fun KeymeQuestionInfo(
    title: String,
    solvedCount: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
            ),
        )

        Text(
            text = "응답자 수 ${solvedCount}명",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                lineHeight = 19.2.sp,
                fontWeight = FontWeight(400),
                color = Color(0x99FFFFFF),
            ),
        )
    }
}

@Composable
private fun KeymeQuestionScoreItem(
    score: String,
    timeStamp: Long,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF303030), shape = RoundedCornerShape(size = 16.dp))
            .padding(vertical = 16.dp, horizontal = 20.dp),
    ) {
        KeymeText(
            modifier = Modifier.align(Alignment.Center),
            text = "${score}점",
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
        )
        KeymeText(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = timeStamp.getUploadTimeString(),
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
        )
    }
}

@Composable
private fun KeymeQuestionStatisticsInfo(
    questionsStatistic: QuestionsStatistic,
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
            text = questionsStatistic.category.name,
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = questionsStatistic.averageScore.toString(),
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
            statistics = QuestionsStatistic(
                averageScore = 0,
                category = Category(
                    color = "",
                    imageUrl = "",
                    name = "",
                ),
                description = "",
                keyword = "",
                questionId = 0,
            ),
            onBackClick = {},
        )
    }
}
