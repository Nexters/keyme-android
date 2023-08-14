package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.keyme.domain.entity.response.Result
import com.keyme.presentation.myprofile.chart.BubbleItem
import com.keyme.presentation.myprofile.chart.rememberBubbleChartState
import timber.log.Timber

@Composable
fun BubbleChart(
    results: List<Result>,
    onBubbleClick: (BubbleItem) -> Unit,
) {
    Timber.d("resultSize: ${results.size}")

    BubbleChartContainer {
        val bubbleChartState = rememberBubbleChartState(
            results = results,
            containerSize = Size(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat(),
            ),
            onBubbleClick = onBubbleClick,
        )

        BubbleChartCanvas(state = bubbleChartState)
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
