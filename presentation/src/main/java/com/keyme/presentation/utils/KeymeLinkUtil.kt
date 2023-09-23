package com.keyme.presentation.utils

object KeymeLinkUtil {
    private const val testLink = "https://keyme-frontend.vercel.app/test/"

    fun getTestLink(testId: Int) = "$testLink$testId"
}
