package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.myprofile.chart.BubbleItem
import com.keyme.presentation.utils.ColorUtil

fun Modifier.bubbleChart() = composed {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var scale by remember { mutableStateOf(1f) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        offset += offsetChange
        scale = (scale * zoomChange).coerceIn(0.6f, 1.8f)
    }

    Modifier
        .fillMaxSize()
        .clipToBounds()
        .transformable(state)
        .graphicsLayer(
            translationX = offset.x,
            translationY = offset.y,
            scaleX = scale,
            scaleY = scale,
        )
}

fun Modifier.bubbleChartItem(item: BubbleItem) = composed {
    Modifier
        .offset(item.offSet.x.dp, item.offSet.y.dp)
        .size(item.size)
        .clip(CircleShape)
        .background(color = ColorUtil.hexStringToColor(item.statistics.category.color), shape = CircleShape)
}
