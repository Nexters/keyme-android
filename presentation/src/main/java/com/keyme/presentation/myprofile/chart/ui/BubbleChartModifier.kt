package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.keyme.presentation.myprofile.chart.BubbleChartState

fun Modifier.bubbleChart(bubbleChartState: BubbleChartState) = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { _, offsetChange, _ ->
        offset += offsetChange
    }

    Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .transformable(state)
        .graphicsLayer(
            translationX = offset.x,
            translationY = offset.y,
        ).pointerInput(Unit) {
            detectTapGestures(
                onTap = { tapOffset ->
                    // todo find rect
                },
            )
        }
}
