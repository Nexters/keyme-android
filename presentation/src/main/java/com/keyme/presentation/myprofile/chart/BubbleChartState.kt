package com.keyme.presentation.myprofile.chart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size

@Composable
fun rememberBubbleChartState(
    containerSize: Size,
): BubbleChartState {
    return remember(Unit) {
        BubbleChartState(containerSize)
    }
}

class BubbleChartState(
    private val containerSize: Size,
) {
    val scale = containerSize.width
}
