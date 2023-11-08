package com.keyme.presentation.utils

object KeymeLinkUtil {
    fun getTestLink(testId: Int): String {
        return if (testId != 0) {
            "${Consts.testLinkUrl}$testId"
        } else {
            ""
        }
    }
}
