package com.keyme.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.keyme.presentation.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
)

val panchang = FontFamily(
    Font(R.font.panchang_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.panchang_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.panchang_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.panchang_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.panchang_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.panchang_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.panchang_semibold, FontWeight.SemiBold, FontStyle.Normal),
)

val pretendard = FontFamily(
    Font(R.font.pretendard_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.pretendard_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.pretendard_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.pretendard_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.pretendard_black, FontWeight.Black, FontStyle.Normal),
)
