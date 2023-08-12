package com.keyme.presentation.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.keyme.presentation.designsystem.theme.pretendard

@Composable
fun KeymeText(
    text: String,
    keymeTextType: KeymeTextType,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = keymeTextType.size.sp,
        fontStyle = null,
        fontWeight = FontWeight(keymeTextType.weight),
        fontFamily = pretendard,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = keymeTextType.lineHeight.sp,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style,
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun KeymeTextPreview() {
    Column {
        KeymeText(
            text = "HEADING_1",
            keymeTextType = KeymeTextType.HEADING_1,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_1",
            keymeTextType = KeymeTextType.BODY_1,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_2",
            keymeTextType = KeymeTextType.BODY_2,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_3_SEMIBOLD",
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_3_REGULAR",
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_4",
            keymeTextType = KeymeTextType.BODY_4,
            color = Color.White,
        )
        KeymeText(
            text = "BODY_5",
            keymeTextType = KeymeTextType.BODY_5,
            color = Color.White,
        )
        KeymeText(
            text = "CAPTION_1",
            keymeTextType = KeymeTextType.CAPTION_1,
            color = Color.White,
        )
        KeymeText(
            text = "TOOLTIP",
            keymeTextType = KeymeTextType.TOOLTIP,
            color = Color.White,
        )
    }
}
