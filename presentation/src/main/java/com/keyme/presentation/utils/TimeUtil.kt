package com.keyme.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

fun Long.getUploadTimeString(): String {
    val uploadInterval = System.currentTimeMillis() - this

    val minutesUnit = TimeUnit.MINUTES.toMillis(1)
    val hourUnit = TimeUnit.HOURS.toMillis(1)
    val dayUnit = TimeUnit.DAYS.toMillis(1)

    val format = SimpleDateFormat("aa hh:mm")

    return when (uploadInterval) {
        in 0L until minutesUnit -> "방금 전"
        in minutesUnit until hourUnit -> "${uploadInterval / minutesUnit}분 전"
        in hourUnit until dayUnit -> "${uploadInterval / hourUnit}시간 전"
        in dayUnit until dayUnit * 2 -> "어제 ${format.format(Date(this))}"
        else -> "엊그제"
    }
}
