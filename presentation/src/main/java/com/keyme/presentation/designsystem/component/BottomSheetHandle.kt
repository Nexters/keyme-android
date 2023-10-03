package com.keyme.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetHandle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.bottomSheetHandle(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .background(color = Color(0x4DFFFFFF), shape = RoundedCornerShape(size = 2.dp)),
        )
    }
}

@Composable
fun Modifier.bottomSheetHandle() = composed {
    Modifier
        .fillMaxWidth()
        .padding(14.dp)
}
