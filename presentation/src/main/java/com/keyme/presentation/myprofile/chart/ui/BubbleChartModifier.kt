package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculateCentroidSize
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateRotation
import androidx.compose.foundation.gestures.calculateZoom
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
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.myprofile.chart.BubbleChartState
import timber.log.Timber
import kotlin.math.PI
import kotlin.math.abs

fun Modifier.bubbleChart(bubbleChartState: BubbleChartState) = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { _, offsetChange, _ ->
        offset += offsetChange
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
        )
}
