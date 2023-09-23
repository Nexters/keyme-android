package com.keyme.presentation.editprofile.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextButton
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.designsystem.theme.keyme_white
import com.keyme.presentation.designsystem.theme.white_alpha_30
import com.keyme.presentation.designsystem.theme.white_alpha_40
import com.keyme.presentation.editprofile.EditProfileUiState
import com.keyme.presentation.editprofile.EditProfileViewModel
import com.keyme.presentation.onboarding.nickname.NicknameInputInfo
import com.keyme.presentation.onboarding.nickname.ProfileImage
import com.keyme.presentation.onboarding.nickname.SignUpToolbar

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    confirmButtonText: String,
) {
    val uiState by viewModel.editProfileUiState.collectAsStateWithLifecycle()

    var nicknameValue by remember { mutableStateOf(uiState.nickname) }
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent(),
    ) { uri -> uri?.let { selectedImage = uri } }

    LaunchedEffect(key1 = selectedImage) {
        selectedImage?.let {
            viewModel.uploadProfileImage(it.toString())
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SignUpToolbar(onBackClick)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            ProfileImage(
                selectedImage = selectedImage,
                onClickImage = { galleryLauncher.launch("image/*") },
            )

            Spacer(modifier = Modifier.size(54.dp))
            NicknameInputInfo(nickname = nicknameValue)

            Spacer(modifier = Modifier.size(10.dp))
            NicknameTextField(
                nickname = nicknameValue,
                onValueChange = {
                    nicknameValue = it
                    viewModel.onNicknameChange(it)
                },
            )

            Spacer(modifier = Modifier.size(12.dp))

            NicknameVerifyResult(uiState)


            Spacer(modifier = Modifier.weight(1f))
            NicknameInputGuide()

            Spacer(modifier = Modifier.size(64.dp))

            KeymeTextButton(
                text = confirmButtonText,
                onClick = {
                    viewModel.updateMember()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                enabled = uiState.isValidNickname(nicknameValue),
            )

            Spacer(modifier = Modifier.size(54.dp))
        }
    }
}

private fun EditProfileUiState.isValidNickname(nicknameValue: String) =
    nicknameValue.length < 2 || isValidNickname

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun NicknameTextField(
    nickname: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = black_alpha_80,
                shape = RoundedCornerShape(4.dp),
            )
            .border(
                border = BorderStroke(1.dp, white_alpha_30),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(
                horizontal = 10.dp,
                vertical = 14.dp,
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.hasFocus
                },
            value = nickname,
            onValueChange = {
                if (it.length <= 6) {
                    onValueChange.invoke(it)
                }
            },
            textStyle = TextStyle(
                color = keyme_white,
                fontSize = KeymeTextType.BODY_3_SEMIBOLD.size.sp,
                fontWeight = FontWeight(KeymeTextType.BODY_3_SEMIBOLD.weight),
                fontFamily = KeymeTextType.BODY_3_SEMIBOLD.fontFamily,
            ),
            cursorBrush = SolidColor(keyme_white),
            decorationBox = { innerTextField ->
                if (nickname.isEmpty()) {
                    KeymeText(
                        text = "닉네임을 입력해주세요",
                        keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
                        modifier = Modifier.fillMaxWidth(),
                        color = white_alpha_40,
                    )
                }
                innerTextField()
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            ),
        )
    }
}


@Composable
private fun NicknameVerifyResult(
    uiState: EditProfileUiState,
) {
    @DrawableRes val inputStatePainter = when (uiState.isValidNickname) {
        true -> R.drawable.icon_circle_passed
        else -> R.drawable.icon_circle_failed
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = inputStatePainter),
            contentDescription = null,
            modifier = Modifier.wrapContentSize(),
        )
        Spacer(modifier = Modifier.size(4.dp))
        KeymeText(
            text = uiState.verifyDescription,
            keymeTextType = KeymeTextType.CAPTION_1,
            color = keyme_white,
        )
    }
}

@Composable
private fun NicknameInputGuide() {
    KeymeText(
        text = "친구들이 원활하게 문제를 풀 수 있도록, 나를 가장 잘 나타내는 닉네임으로 설정해주세요",
        keymeTextType = KeymeTextType.BODY_4,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Color(0x0DFFFFFF),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(all = 16.dp),
        color = keyme_white,
    )
}

