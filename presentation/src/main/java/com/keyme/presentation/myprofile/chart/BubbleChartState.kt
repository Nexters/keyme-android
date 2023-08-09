package com.keyme.presentation.myprofile.chart

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Path
import com.keyme.domain.entity.response.Circle
import com.keyme.presentation.utils.scale
import timber.log.Timber

@Composable
fun rememberBubbleChartState(
    circles: List<Circle>,
    containerSize: Size,
): BubbleChartState {
    return remember(Unit) {
        BubbleChartState(circles, containerSize)
    }
}

class BubbleChartState(
    private val circles: List<Circle>,
    private val containerSize: Size,
) {
    val scale = containerSize.width.toInt()
    val bubbleRectList = circles.map {circle ->
        Rect(
            center = Offset(
                x = circle.x.scale(scale) + containerSize.center.x,
                y = circle.y.scale(scale) + containerSize.center.y,
            ),
            radius = circle.r.scale(scale),
        )
    }

    fun onBubbleItemClick(item: Rect) {
        Timber.d("onBubbleItemClick: $item")


    }
}
