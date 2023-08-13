package com.keyme.presentation.myprofile.chart.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.keyme.presentation.myprofile.chart.BubbleChartState
import timber.log.Timber

@Composable
fun BubbleChartCanvas(
    bubbleChartState: BubbleChartState,
) {
    Canvas(
        modifier = Modifier.bubbleChart(bubbleChartState),
        onDraw = {
            repeat(bubbleChartState.bubbleRectList.size) { index ->
                val rect = bubbleChartState.bubbleRectList[index]
                val color = bubbleChartState.colors[index]
//                val itemBitmap = bubbleChartState.bubbleChartItemBitmaps[index]

                drawResultBubble(rect, color)
//                drawResultItem(rect, itemBitmap)
            }
        },
    )
}

private val colors = listOf(
    Color.Blue,
    Color.Green,
    Color.Gray,
    Color.Red,
    Color.LightGray,
    Color.Green,
    Color.Cyan,
    Color.Yellow,
    Color.Red,
    Color.LightGray,
)

private fun DrawScope.drawResultBubble(
    bubbleRect: Rect,
    color: Color,
) {
    val circlePath = Path().apply { addOval(bubbleRect) }
    drawPath(circlePath, color)
}

private fun DrawScope.drawResultItem(bubbleRect: Rect, item: Bitmap) {
    val targetX = bubbleRect.center.x - (item.width / 2)
    val targetY = bubbleRect.center.y - (item.height / 2)
    val targetOffset = Offset(targetX, targetY)

    drawImage(image = item.asImageBitmap(), topLeft = targetOffset)
}
