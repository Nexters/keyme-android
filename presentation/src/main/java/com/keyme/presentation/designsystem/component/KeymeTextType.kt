package com.keyme.presentation.designsystem.component

import androidx.compose.ui.text.font.FontFamily
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.designsystem.theme.pretendard

enum class KeymeTextType(
    val size: Int,
    val weight: Int,
    val lineHeight: Float,
    val fontFamily: FontFamily,
) {
    HEADING_1(
        size = 24,
        weight = 600,
        lineHeight = 31.2f,
        fontFamily = pretendard,
    ),
    BODY_1(
        size = 20,
        weight = 600,
        lineHeight = 26f,
        fontFamily = pretendard,
    ),
    BODY_2(
        size = 18,
        weight = 600,
        lineHeight = 25.2f,
        fontFamily = pretendard,
    ),
    BODY_3_SEMIBOLD(
        size = 16,
        weight = 600,
        lineHeight = 22.4f,
        fontFamily = pretendard,
    ),
    BODY_3_REGULAR(
        size = 16,
        weight = 400,
        lineHeight = 19.2f,
        fontFamily = pretendard,
    ),
    BODY_4(
        size = 14,
        weight = 600,
        lineHeight = 18.2f,
        fontFamily = pretendard,
    ),
    BODY_5(
        size = 12,
        weight = 600,
        lineHeight = 14.4f,
        fontFamily = pretendard,
    ),
    CAPTION_1(
        size = 12,
        weight = 500,
        lineHeight = 14.4f,
        fontFamily = pretendard,
    ),
    TOOLTIP(
        size = 12,
        weight = 400,
        lineHeight = 20f,
        fontFamily = pretendard,
    ),
    SCORE_RESULT(
        size = 32,
        weight = 800,
        lineHeight = 38.4f,
        fontFamily = panchang,
    ),
    SCORE_DETAIL(
        size = 40,
        weight = 800,
        lineHeight = 48f,
        fontFamily = panchang,
    ),
    SCORE_MY_PAGE(
        size = 18,
        weight = 800,
        lineHeight = 21.6f,
        fontFamily = panchang,
    )
}
