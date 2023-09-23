package com.keyme.presentation.utils

import android.content.Context
import android.content.Intent

fun Context.startShareActivity(data: String) {
    Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, data)
    }.let {
        startActivity(Intent.createChooser(it, data))
    }
}
