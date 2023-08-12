package com.keyme.presentation.designsystem.component

enum class KeymeTextType(
    val size: Int,
    val weight: Int,
    val lineHeight: Float,
) {
    HEADING_1(
        size = 24,
        weight = 600,
        lineHeight = 31.2f,
    ),
    BODY_1(
        size = 20,
        weight = 600,
        lineHeight = 26f,
    ),
    BODY_2(
        size = 18,
        weight = 600,
        lineHeight = 25.2f,
    ),
    BODY_3_SEMIBOLD(
        size = 16,
        weight = 600,
        lineHeight = 22.4f,
    ),
    BODY_3_REGULAR(
        size = 16,
        weight = 400,
        lineHeight = 19.2f,
    ),
    BODY_4(
        size = 14,
        weight = 600,
        lineHeight = 18.2f,
    ),
    BODY_5(
        size = 12,
        weight = 600,
        lineHeight = 14.4f,
    ),
    CAPTION_1(
        size = 12,
        weight = 500,
        lineHeight = 14.4f,
    ),
    TOOLTIP(
        size = 12,
        weight = 400,
        lineHeight = 20f,
    )
}
