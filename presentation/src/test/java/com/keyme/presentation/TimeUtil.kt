package com.keyme.presentation

import com.keyme.presentation.utils.getUploadTimeStamp
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

class TimeUtil {
    @Test
    fun uploadTimeStamp() {
        val now = System.currentTimeMillis()

        val minutesUnit = TimeUnit.MINUTES.toMillis(1)
        val hourUnit = TimeUnit.HOURS.toMillis(1)
        val dayUnit = TimeUnit.DAYS.toMillis(1)

        assertEquals("방금 전", (now - (minutesUnit - 100L)).getUploadTimeStamp())
        assertEquals("3분 전", (now - (minutesUnit * 3)).getUploadTimeStamp())
        assertEquals("3시간 전", (now - (hourUnit * 3)).getUploadTimeStamp())
        assertEquals("엊그제", (now - (dayUnit * 3)).getUploadTimeStamp())
    }
}
