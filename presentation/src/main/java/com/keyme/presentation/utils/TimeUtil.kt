package com.keyme.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

private const val ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd'T'HH:mm:ss.SSS"

fun String.toTimeStamp(): Long {
    val simpleDateFormat = SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET, Locale.getDefault())
    return simpleDateFormat.parse(this)?.time ?: 0L
}

fun Long.getUploadTimeString(): String {
    val uploadInterval = System.currentTimeMillis() - this

    val minutesUnit = TimeUnit.MINUTES.toMillis(1)
    val hourUnit = TimeUnit.HOURS.toMillis(1)
    val dayUnit = TimeUnit.DAYS.toMillis(1)

    val timeFormat = SimpleDateFormat("aa hh:mm", Locale.getDefault())
    val dateFormat =  SimpleDateFormat("M월 d일", Locale.getDefault())

    return when (uploadInterval) {
        in 0L until minutesUnit -> "방금 전"
        in minutesUnit until hourUnit -> "${uploadInterval / minutesUnit}분 전"
        in hourUnit until dayUnit -> "${uploadInterval / hourUnit}시간 전"
        in dayUnit until dayUnit * 2 -> "어제 ${timeFormat.format(Date(this))}"
        else -> dateFormat.format(Date(this))
    }
}
