package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.keyme.domain.entity.response.Circle
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.myprofile.chart.rememberBubbleChartState

@Composable
fun BubbleChart(circles: List<Circle>) {
    BubbleChartContainer {
        val bubbleChartState = rememberBubbleChartState(
            circles = circles,
            containerSize = Size(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat(),
            ),
        )

        if (bubbleChartState.bubbleChartInitialState.isInit()) bubbleChartState.init()

        if (bubbleChartState.bubbleChartInitialState.isFinish()) {
            BubbleChartCanvas(bubbleChartState = bubbleChartState)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = keyme_black)
                    .zIndex(1f),
            )
        }
    }
}

@Composable
private fun BubbleChartContainer(
    content: @Composable @UiComposable BoxWithConstraintsScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content,
    )
}
