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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.myprofile.chart.BubbleChartState
import timber.log.Timber

fun Modifier.bubbleChart(bubbleChartState: BubbleChartState) = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var scale by remember { mutableStateOf(1f) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        offset += offsetChange
        scale = (scale * zoomChange).coerceIn(0.6f, 1.8f)
    }

    Modifier
        .fillMaxSize()
        .background(color = keyme_black)
        .clipToBounds()
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { tapOffset ->
                    Timber.d("offset: $offset, tapOffset: $tapOffset")

                    val tapOffset = tapOffset - offset
                    bubbleChartState.bubbleRectList
                        .find { it.contains(tapOffset) }
                        ?.let {
                            bubbleChartState.onBubbleItemClick(it)
                        }
                },
            )
        }
        .transformable(state)
        .graphicsLayer(
            translationX = offset.x,
            translationY = offset.y,
            scaleX = scale,
            scaleY = scale,
        )
}
