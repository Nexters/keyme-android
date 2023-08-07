package com.keyme.presentation.myprofile.ui

import androidx.compose.runtime.Composable
import com.keyme.domain.entity.response.Circle
import com.keyme.presentation.myprofile.chart.ui.BubbleChart

@Composable
fun ResultScreen(
    circles: List<Circle>,
) {
    BubbleChart(circles = circles)
}
