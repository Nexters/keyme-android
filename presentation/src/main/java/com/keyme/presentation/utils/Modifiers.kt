package com.keyme.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

fun Modifier.clickableRippleEffect(bounded: Boolean = true, onClick: () -> Unit) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded),
        onClick = { onClick() },
    )
}

fun Modifier.topBorder(width: Dp, color: Color) = composed {
    drawBehind {
        val strokeWidth = width.value * density
        val y = strokeWidth / 2

        drawLine(
            color,
            Offset(0f, y),
            Offset(size.width, y),
            strokeWidth,
        )
    }
}
