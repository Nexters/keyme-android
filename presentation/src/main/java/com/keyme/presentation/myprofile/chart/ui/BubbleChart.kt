package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.geometry.Size
import com.keyme.domain.entity.response.Circle
import com.keyme.presentation.myprofile.chart.rememberBubbleChartState

@Composable
fun BubbleChart(circles: List<Circle>) {
    BubbleChartContainer {
        val bubbleChartState = rememberBubbleChartState(
            containerSize = Size(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat(),
            ),
        )

        BubbleChartCanvas(circles = circles, bubbleChartState = bubbleChartState)
    }
}

@Composable
private fun BubbleChartContainer(
    content: @Composable @UiComposable BoxWithConstraintsScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = content,
    )
}


//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun DrawSample() {
//    val result = circleDummy()
//    var size by remember {
//        mutableStateOf(IntSize.Zero)
//    }
//    val scale = size.width
//
//    val colors = listOf(
//        Color.Blue,
//        Color.Green,
//        Color.Gray,
//        Color.Red,
//        Color.LightGray,
//        Color.Green,
//        Color.Cyan,
//        Color.Yellow,
//        Color.Red,
//        Color.LightGray,
//    )
//
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
//        offset += offsetChange
//    }
//
//    val rects = mutableListOf<Rect>()
//    val rr = rememberUpdatedState(newValue = rects)
//
//    val image = ImageVector.vectorResource(id = com.keyme.presentation.R.drawable.icon_camera)
//
//    var center by remember {
//        mutableStateOf(Offset.Zero)
//    }
//
//    val context = LocalContext.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Black)
//            .onGloballyPositioned {
//                size = it.size
//                center = it.boundsInWindow().center
//            }
//            .transformable(state)
//            .graphicsLayer(
//                translationX = offset.x,
//                translationY = offset.y,
//            ),
//    ) {
//        Canvas(
//            modifier = Modifier
//                .fillMaxSize()
//                .graphicsLayer(
//                    translationX = offset.x,
//                    translationY = offset.y,
//                )
//                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onTap = { tapOffset ->
//                            rr.value
//                                .find { it.contains(tapOffset) }
//                                ?.let {
//                                    Log.d("><>", it.toString())
//                                }
//                        },
//                    )
//                },
//            onDraw = {
//                rects.clear()
//                result.forEachIndexed { index, circle ->
//                    drawResultBubble(circle, colors[index], scale, "")
//                }
//            },
//        )
//
//        rects.forEach {
//            Log.d("><>", it.center.toString())
//            Column {
//                Text(text = "aasdf", modifier = Modifier.offset(it.center.x.dp, it.center.y.dp))
//            }
//        }
//    }
//}


