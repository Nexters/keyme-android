package com.keyme.presentation.utils

import androidx.compose.ui.graphics.Color
import timber.log.Timber

object ColorUtil {
    fun hexStringToColor(value: String): Color {
        return if (value.length == 6) {
            val alpha = 255
            val red = value.substring(0, 2).toInt(16)
            val green = value.substring(2, 4).toInt(16)
            val blue = value.substring(4, 6).toInt(16)

            val argbColor = (alpha and 0xFF shl 24) or
                (red and 0xFF shl 16) or
                (green and 0xFF shl 8) or
                (blue and 0xFF)

            Timber.d("argbColor = $argbColor")

            Color(argbColor)
        } else {
            Color.Unspecified
        }
    }
}
