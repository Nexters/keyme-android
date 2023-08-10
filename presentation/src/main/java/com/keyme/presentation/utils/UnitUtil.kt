package com.keyme.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun Int.textDp(): TextUnit = with(LocalDensity.current) {
    this@textDp.dp.toSp()
}
