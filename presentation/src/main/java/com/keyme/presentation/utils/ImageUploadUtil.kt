package com.keyme.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import timber.log.Timber
import java.io.ByteArrayOutputStream

object ImageUploadUtil {

    fun getResizedBase64EncodedString(context: Context, uri: Uri): String? {
        val resized = resize(context, uri, 360)
        val result = resized?.let {
            val compressed = compress(it)
            Timber.d("><> compressed: ${compressed.byteCount}")
            getStringImage(compressed)
        }

        return result
    }

    private fun resize(context: Context, uri: Uri, resizePx: Int): Bitmap? {
        val options = BitmapFactory.Options()

        BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options)
        var width = options.outWidth
        var height = options.outHeight
        var samplesize = 1
        while (width > resizePx || height > resizePx) {
            width /= 2
            height /= 2
            samplesize *= 2
        }
        options.inSampleSize = samplesize

        val resized = BitmapFactory.decodeStream(
            context.contentResolver.openInputStream(uri),
            null,
            options,
        )

        return resized?.let {
            Bitmap.createScaledBitmap(resized, width, height, true)
        }
    }

    private fun compress(bitmap: Bitmap): Bitmap {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)

        return BitmapFactory.decodeByteArray(
            baos.toByteArray(),
            0,
            baos.toByteArray().size,
        )
    }

    private fun getStringImage(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }
}
