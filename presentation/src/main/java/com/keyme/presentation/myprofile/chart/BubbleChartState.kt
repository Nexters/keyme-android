package com.keyme.presentation.myprofile.chart

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.keyme.domain.entity.response.Coordinate
import com.keyme.presentation.utils.scale
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@Composable
fun rememberBubbleChartState(
    coordinates: List<Coordinate>,
    colors: List<Color>,
    containerSize: Size,
    onBubbleClick: () -> Unit,
): BubbleChartState {
    val coroutineScope = rememberCoroutineScope()
    return remember(coordinates, containerSize) {
        BubbleChartState(coroutineScope, coordinates, colors, containerSize, onBubbleClick)
    }
}

enum class BubbleChartInitialState {
    Init, Loading, Finish;

    fun isInit() = this == Init

    fun isFinish() = this == Finish
}

class BubbleChartState(
    private val coroutineScope: CoroutineScope,
    val coordinates: List<Coordinate>,
    val colors: List<Color>,
    private val containerSize: Size,
    private val onBubbleClick: () -> Unit,
) {
    private val scale = containerSize.width.toInt()
    val bubbleRectList = coordinates.map { coordinate ->
        Rect(
            center = Offset(
                x = coordinate.x.scale(scale) + containerSize.center.x,
                y = coordinate.y.scale(scale) + containerSize.center.y,
            ),
            radius = coordinate.r.scale(scale),
        )
    }

    var bubbleChartInitialState by mutableStateOf(BubbleChartInitialState.Init)

    val bubbleChartItemBitmaps = mutableListOf<Bitmap>()

    @Composable
    fun init() {
        Timber.d("init()")
        if (coordinates.isNotEmpty()) {
            bubbleChartInitialState = BubbleChartInitialState.Loading

            val snapShotSets = bubbleRectList.map {
                CaptureBitmap(content = { BubbleChartItem() })
            }

//            LaunchedEffect(key1 = bubbleRectList) {
//                while (bubbleChartInitialState.isFinish().not()) {
//                    val item = snapShotSets.firstOrNull()
//                    if (item != null) {
//                        val bitmap = kotlin.runCatching { item() }.onFailure { Timber.e(it) }.getOrNull()
//                        bitmap?.let { bubbleChartItemBitmaps.add(bitmap) }
//                    } else {
//                        bubbleChartInitialState = BubbleChartInitialState.Finish
//                        Timber.d("bubbleChartInitialState: $bubbleChartInitialState, bitmaps: ${bubbleChartItemBitmaps.size}")
//                    }
//                }
//            }

            bubbleChartInitialState = BubbleChartInitialState.Finish
        }
    }

    fun onBubbleItemClick(item: Rect) {
        Timber.d("onBubbleItemClick: $item")
        onBubbleClick()
    }
}

@Composable
private fun CaptureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {
    val context = LocalContext.current
    val composeView = remember { ComposeView(context) }

    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                }
            }
        },
    )

    return ::captureBitmap
}

@Composable
private fun BubbleChartItem() {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = CircleShape,
            )
            .background(
                color = Color(0x17171799),
                shape = CircleShape,
            )
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text("표현력\n4.0점", textAlign = TextAlign.Center)
    }
}
