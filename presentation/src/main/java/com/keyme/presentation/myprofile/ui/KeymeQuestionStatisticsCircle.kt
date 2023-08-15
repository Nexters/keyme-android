package com.keyme.presentation.myprofile.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.keymetest.QuestionsStatistic
import com.keyme.presentation.utils.ColorUtil
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ColumnScope.KeymeQuestionStatisticsCircle(
    statistics: QuestionsStatistic,
    myScore: QuestionSolvedScore?,
    onPressedUp: () -> Unit,
    onPressedDown: () -> Unit,
) {
    val density = LocalDensity.current
    var containerCircleSize by remember { mutableStateOf(IntSize.Zero) }
    val avgScoreCircleSize = DpSize(
        width = with(density) { ((containerCircleSize.width / 5) * statistics.averageScore).toDp() },
        height = with(density) { ((containerCircleSize.height / 5) * statistics.averageScore).toDp() },
    )

    val myScoreCircleSize = if (myScore != null) {
        DpSize(
            width = with(density) { ((containerCircleSize.width / 5) * myScore.score).toDp() },
            height = with(density) { ((containerCircleSize.height / 5) * myScore.score).toDp() },
        )
    } else {
        DpSize.Zero
    }

    var showMyScore by remember(myScore) { mutableStateOf(false) }

    Timber.d("showMyScore: $showMyScore")

    Box(
        modifier = Modifier
            .questionMatchRateCircle(
                this,
                myScore,
                onPressedUp = {
                    showMyScore = true
                    onPressedUp()
                },
                onPressedDown = {
                    showMyScore = false
                    onPressedDown()
                },
            )
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

        androidx.compose.animation.AnimatedVisibility(
            visible = showMyScore,
            enter = scaleIn(),
            exit = scaleOut(),
        ) {
            Box(
                modifier = Modifier
                    .size(myScoreCircleSize)
                    .background(
                        color = Color(0x99000000),
                        shape = CircleShape,
                    )
                    .clip(CircleShape),
            )
        }

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
private fun Modifier.questionMatchRateCircle(
    scope: ColumnScope,
    myScore: QuestionSolvedScore?,
    onPressedDown: () -> Unit,
    onPressedUp: () -> Unit,
) = composed {
    with(scope) {
        Modifier
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
            .pointerInput(myScore) {
                detectTapGestures(
                    onPress = {
                        if (myScore != null) {
                            onPressedUp()
                            this.awaitRelease()
                            onPressedDown()
                        }
                    },
                )
            }
    }
}
