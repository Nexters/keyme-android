package com.keyme.presentation.onboarding.nickname

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextButton
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.black_alpha_60
import com.keyme.presentation.designsystem.theme.gray400
import com.keyme.presentation.designsystem.theme.keyme_white
import com.keyme.presentation.designsystem.theme.gray500

@Composable
fun NicknameScreen(
    onBackClick: () -> Unit,
    onClickNextButton: () -> Unit,
) {
    val contentHorizontalPadding = 16

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black_alpha_60),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NicknameToolbar(onBackClick)

        Spacer(modifier = Modifier.size(20.dp))
        ProfileImage()

        Spacer(modifier = Modifier.size(54.dp))
        NicknameInputInfo(contentHorizontalPadding)

        Spacer(modifier = Modifier.size(10.dp))
        NicknameTextField(contentHorizontalPadding)

        Spacer(modifier = Modifier.size(12.dp))
        NicknameInputResult(
            result = true,
            contentHorizontalPadding = contentHorizontalPadding,
        )

        Spacer(modifier = Modifier.weight(1f))
        KeymeTextButton(
            text = "다음",
            onClick = onClickNextButton,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            enabled = true,
        )

        Spacer(modifier = Modifier.size(54.dp))
    }
}

@Composable
fun NicknameToolbar(
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back_arrow),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 12.dp)
                .padding(vertical = 12.dp)
                .clickable { onBackClick() },
            tint = Color.White,
        )
        KeymeText(
            text = "회원가입",
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            color = keyme_white,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ProfileImage(
//    imageUri: Uri,
) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .background(
                color = Color.DarkGray,
                shape = CircleShape,
            )
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = CircleShape,
            ),
    ) {
        // TODO: Set image.
    }
}

@Composable
fun NicknameInputInfo(
    contentHorizontalPadding: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = contentHorizontalPadding.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        KeymeText(
            text = "닉네임",
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            modifier = Modifier.wrapContentSize(),
            color = keyme_white,
        )
        Spacer(modifier = Modifier.size(4.dp))
        KeymeText(
            text = "(0/6)",
            keymeTextType = KeymeTextType.CAPTION_1,
            modifier = Modifier.wrapContentSize(),
            color = gray500,
        )
        Spacer(modifier = Modifier.weight(1f))
        KeymeText(
            text = "2-6자리 한글, 영어, 숫자",
            keymeTextType = KeymeTextType.CAPTION_1,
            modifier = Modifier.wrapContentSize(),
            color = keyme_white,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NicknameTextField(
    contentHorizontalPadding: Int,
    onChangedTextFieldFocus: (Boolean) -> Unit = {},
) {
    var value by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = contentHorizontalPadding.dp)
            .onFocusChanged {
                onChangedTextFieldFocus.invoke(it.hasFocus)
            },
        textStyle = LocalTextStyle.current.copy(
            color = Color.Red,
            fontSize = KeymeTextType.BODY_3_SEMIBOLD.size.sp,
            fontWeight = FontWeight(KeymeTextType.BODY_3_SEMIBOLD.weight),
            fontFamily = KeymeTextType.BODY_3_SEMIBOLD.fontFamily,
        ),
//        TextStyle(
//            color = Color.Red,
//            fontSize = KeymeTextType.BODY_3_SEMIBOLD.size.sp,
//            fontWeight = FontWeight(KeymeTextType.BODY_3_SEMIBOLD.weight),
//            fontFamily = KeymeTextType.BODY_3_SEMIBOLD.fontFamily
//        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() },
        ),
        singleLine = true,
        decorationBox = {
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 4.dp),
                    )
                    .border(
                        width = 1.dp,
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(size = 4.dp),
                    )
                    .padding(
                        horizontal = 10.dp,
                        vertical = 14.dp,
                    ),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty()) {
                    KeymeText(
                        text = "닉네임을 입력해주세요",
                        keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                        modifier = Modifier.wrapContentSize(),
                        color = gray400,
                    )
                } else {
                    KeymeText(
                        text = value,
                        keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                        modifier = Modifier.wrapContentSize(),
                        color = keyme_white,
                    )
                }
            }
//            innerTextField()
        },
        cursorBrush = Brush.verticalGradient(colors = listOf(keyme_white, keyme_white)),
    )
}

@SuppressLint("SupportAnnotationUsage")
@Composable
fun NicknameInputResult(
    result: Boolean?,
    contentHorizontalPadding: Int,
) {
//    @DrawableRes val inputStatePainter = when (result) {
//        true -> R.drawable.icon_circle_passed
//        false -> R.drawable.icon_circle_failed
//        else -> null
//    }
//    @StringRes val inputStateInfo = when (result) {
//        true -> R.drawable.icon_circle_passed
//        false -> R.drawable.icon_circle_failed
//        else -> null
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .padding(horizontal = contentHorizontalPadding.dp),
//    ) {
//        inputStatePainter?.let {
//            Icon(
//                painter = painterResource(id = it),
//                contentDescription = null,
//            modifier = Modifier.wrapContentSize(),
//                contentScale = ContentScale.Crop,
//            )
//        }
//    }
}

@Composable
@Preview(showBackground = true)
fun NicknameScreenPreview() {
    NicknameScreen(
        onBackClick = {},
        onClickNextButton = {},
    )
}
