package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.presentation.myprofile.chart.ui.BubbleChart

@Composable
fun KeymeMemberStatisticsScreen(
    memberStatistics: MemberStatistics,
    onQuestionClick: (QuestionStatistic) -> Unit,
) {
    BubbleChart(
        results = memberStatistics.results,
        onBubbleClick = {
            onQuestionClick(it.statistics)
        },
    )
}
