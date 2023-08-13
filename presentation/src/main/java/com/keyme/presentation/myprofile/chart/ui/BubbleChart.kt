package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.zIndex
import com.keyme.domain.entity.response.Result
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.myprofile.chart.rememberBubbleChartState
import com.keyme.presentation.utils.ColorUtil
import timber.log.Timber

@Composable
fun BubbleChart(
    results: List<Result>,
    onBubbleClick: () -> Unit,
) {
    Timber.d("resultSize: ${results.size}")

    BubbleChartContainer {
        val bubbleChartState = rememberBubbleChartState(
            coordinates = results.map { it.coordinate },
            colors = results.map {
                ColorUtil.hexStringToColor(it.question.category.color)
            },
            containerSize = Size(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat(),
            ),
            onBubbleClick = onBubbleClick,
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
    content: @Composable BoxWithConstraintsScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content,
    )
}
