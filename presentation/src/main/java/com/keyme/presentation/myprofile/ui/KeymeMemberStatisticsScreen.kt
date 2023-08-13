package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.presentation.myprofile.chart.ui.BubbleChart

@Composable
fun KeymeMemberStatisticsScreen(
    memberStatistics: MemberStatistics,
    onTestItemClick: () -> Unit,
) {
    BubbleChart(
        results = memberStatistics.results,
        onBubbleClick = {
            onTestItemClick()
        },
    )
}
