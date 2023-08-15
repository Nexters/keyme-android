package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.designsystem.component.BottomSheetHandle
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.utils.getUploadTimeString
import timber.log.Timber
import java.sql.Timestamp


@Composable
fun ColumnScope.KeymeQuestionSolvedScoreList(
    myCharacter: Member,
    statistic: QuestionStatistic,
    solvedScorePagingItem: LazyPagingItems<QuestionSolvedScore>,
) {
    KeymeQuestionScoreListContainer {
        KeymeQuestionInfo(
            title = "${myCharacter.nickname}님의 ${statistic.keyword}정도는?",
            solvedCount = solvedScorePagingItem.itemCount,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0x1AFFFFFF))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                count = solvedScorePagingItem.itemCount,
                key = solvedScorePagingItem.itemKey(key = { item -> item.id }),
            ) {
                val item = solvedScorePagingItem[it]
                item?.let {
                    KeymeQuestionScoreItem(item.score.toString(), Timestamp.valueOf(item.createAt).time)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.KeymeQuestionScoreListContainer(content: @Composable ColumnScope.() -> Unit) {
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
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
            ),
        )

        Text(
            text = "응답자 수 ${solvedCount}명",
            style = TextStyle(
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
