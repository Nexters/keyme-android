package com.keyme.presentation.myprofile.chart.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.myprofile.chart.BubbleChartState
import com.keyme.presentation.myprofile.chart.BubbleItem
import com.keyme.presentation.utils.textDp

@Composable
fun BubbleChartCanvas(
    state: BubbleChartState,
) {
    Box(modifier = Modifier.bubbleChart()) {
        state.bubbleItems.forEach {
            BubbleChartItem(bubbleItem = it, onClick = { state.onBubbleItemClick(it) })
        }
    }
}

@Composable
fun BubbleChartItem(
    bubbleItem: BubbleItem,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .bubbleChartItem(bubbleItem)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = Modifier.size(48.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(bubbleItem.statistics.category.iconUrl)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(6.dp))

            KeymeText(
                text = bubbleItem.statistics.category.name,
                keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                color = Color.White,
            )

            Text(
                text = bubbleItem.statistics.avgScore.toString(),
                fontFamily = panchang,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.textDp(),
                color = Color.White,
            )
        }
    }
}
