package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.keyme.domain.entity.response.Circle
import com.keyme.presentation.myprofile.chart.BubbleChartState
import com.keyme.presentation.utils.scale

@Composable
fun BubbleChartCanvas(
    circles: List<Circle>,
    bubbleChartState: BubbleChartState,
) {
    Canvas(
        modifier = Modifier.bubbleChart(bubbleChartState),
        onDraw = {
            circles.forEachIndexed { index, circle ->
                drawResultBubble(circle, colors[index], bubbleChartState.scale.toInt(), "")
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

private fun DrawScope.drawResultBubble(circle: Circle, color: Color, scale: Int, info: String) {
    val center = Offset(
        x = circle.x.scale(scale) + center.x,
        y = circle.y.scale(scale) + center.y,
    )

    val circlePath = Path().apply {
        val rect = Rect(
            center = center,
            radius = circle.r.scale(scale),
        )
        addOval(rect)
    }

    drawPath(circlePath, color)
}

@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawResultInfo(textMeasurer: TextMeasurer, offset: Offset, info: String) {
    drawText(
        textMeasurer = textMeasurer,
        text = info,
    )
}
