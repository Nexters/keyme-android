package com.keyme.presentation.myprofile.chart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.keyme.domain.entity.response.Question
import com.keyme.domain.entity.response.Result
import com.keyme.presentation.utils.scale
import timber.log.Timber

@Composable
fun rememberBubbleChartState(
    results: List<Result>,
    containerSize: Size,
    onBubbleClick: (BubbleItem) -> Unit,
): BubbleChartState {
    val density = LocalDensity.current.density
    return remember(results, containerSize) {
        BubbleChartState(density, results, containerSize, onBubbleClick)
    }
}

class BubbleChartState(
    private val density: Float,
    private val results: List<Result>,
    private val containerSize: Size,
    private val onBubbleClick: (BubbleItem) -> Unit,
) {
    private val scale = containerSize.width.toInt()
    val bubbleItems = results.map { it.toBubbleItem() }

    private fun Result.toBubbleItem(): BubbleItem {
        val rect = Rect(
            center = Offset(
                x = coordinate.x.scale(scale) + containerSize.center.x,
                y = coordinate.y.scale(scale) + containerSize.center.y,
            ),
            radius = coordinate.r.scale(scale),
        )

        val offsetX = rect.topLeft.x / density
        val offsetY = rect.topLeft.y / density
        val dpSize = (rect.size.width / density).dp

        return BubbleItem(question, Offset(offsetX, offsetY), dpSize)
    }

    fun onBubbleItemClick(item: BubbleItem) {
        Timber.d("onBubbleItemClick: $item")
        onBubbleClick(item)
    }
}

data class BubbleItem(
    val question: Question,
    val offSet: Offset,
    val size: Dp,
)
