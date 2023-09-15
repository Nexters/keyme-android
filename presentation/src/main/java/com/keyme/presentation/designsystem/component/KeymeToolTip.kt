package com.keyme.presentation.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.keyme.presentation.R

@Composable
fun KeymeToolTip(text: String, onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.tool_tip_box),
                sizeToIntrinsics = true,
                contentScale = ContentScale.FillBounds,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCloseClick() },
            )
            .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
    ) {
        KeymeText(
            modifier = Modifier.weight(1f),
            text = text,
            keymeTextType = KeymeTextType.TOOLTIP,
            color = Color(0xFFF6F3F2),
        )
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .size(12.dp),
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = "",
            tint = Color.White,
        )
    }
}
