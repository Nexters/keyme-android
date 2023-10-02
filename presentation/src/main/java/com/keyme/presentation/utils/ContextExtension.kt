package com.keyme.presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import timber.log.Timber

fun Context.startShareActivity(data: String) {
    Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, data)
    }.let {
        startActivity(Intent.createChooser(it, data))
    }
}

fun Context.startWebView(url: String) {
    val uri = kotlin.runCatching {
        Uri.parse(url)
    }.onFailure { Timber.e(it) }.getOrNull()
    uri?.let {
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
