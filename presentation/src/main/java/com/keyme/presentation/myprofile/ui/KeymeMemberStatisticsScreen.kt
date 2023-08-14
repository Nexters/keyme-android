package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.Question
import com.keyme.presentation.myprofile.chart.ui.BubbleChart

@Composable
fun KeymeMemberStatisticsScreen(
    memberStatistics: MemberStatistics,
    onQuestionClick: (Question) -> Unit,
) {
    BubbleChart(
        results = memberStatistics.results,
        onBubbleClick = {
            onQuestionClick(it.question)
        },
    )
}


