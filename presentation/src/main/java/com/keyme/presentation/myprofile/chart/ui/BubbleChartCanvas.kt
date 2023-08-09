package com.keyme.presentation.myprofile.chart.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.keyme.presentation.myprofile.chart.BubbleChartState

@Composable
fun BubbleChartCanvas(
    bubbleChartState: BubbleChartState,
) {
    Canvas(
        modifier = Modifier.bubbleChart(bubbleChartState),
        onDraw = {
            repeat(bubbleChartState.bubbleRectList.size) {index ->
                val rect = bubbleChartState.bubbleRectList[index]
                val itemBitmap = bubbleChartState.bubbleChartItemBitmaps[index]

                drawResultBubble(rect, colors[index])
                drawResultItem(rect, itemBitmap)
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
