package com.keyme.presentation.signin

import android.content.Context
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.keyme.presentation.R
import com.keyme.presentation.UiState
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_white
import com.keyme.presentation.designsystem.theme.white_alpha_50
import com.keyme.presentation.signin.enums.SignInStateEnum

@Composable
fun SignInRoute(
    navigateToNickname: () -> Unit,
//    navigateToKeymeTest: () -> Unit,
//    navigateToMyDaily: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val signInState: UiState<SignInStateEnum> by viewModel.keymeSignInState.collectAsState()

    when (val state = signInState) {
        is UiState.Loading -> {}
        is UiState.Success<SignInStateEnum> -> {
            when (state.data) {
                SignInStateEnum.NICKNAME -> navigateToNickname.invoke()
//                SignInStateEnum.KEYME_TEST -> navigateToKeymeTest.invoke()
//                SignInStateEnum.MY_DAILY -> navigateToMyDaily.invoke()
                else -> {}
            }
        }
        is UiState.ApiError -> {}
        is UiState.Failure -> {}
    }

    SignInScreen(
        onClickKakaoSignIn = {
            signInWithKakao(
                context,
                viewModel::signInWithKeyme,
            )
        },
    )
}

@Composable
fun SignInScreen(
    onClickKakaoSignIn: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        // TODO: Set animation background.
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            KakaoSignInButton(onClickKakaoSignIn)
            Spacer(modifier = Modifier.size(30.dp))
            SignInPoliciesText(
                onClickServicePolicy = { /*TODO*/ },
                onCLickPrivacyPolicy = { /*TODO*/ },
            )
        }
    }
}

@Composable
fun KakaoSignInButton(
    onClickKakaoSignIn: () -> Unit,
) {
    Image(
        painter = painterResource(id = R.drawable.button_kakao_sign_in),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 32.dp)
            .clip(shape = RoundedCornerShape(6.dp))
            .clickable { onClickKakaoSignIn() },
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun SignInPoliciesText(
    onClickServicePolicy: () -> Unit,
    onCLickPrivacyPolicy: () -> Unit,
) {
    KeymeText(
        text = "가입 시, 키미의 다음 사항에 동의하는 것으로 간주합니다.",
        keymeTextType = KeymeTextType.BODY_5,
        modifier = Modifier.wrapContentSize(align = Alignment.Center),
        color = white_alpha_50,
    )
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 56.dp),
    ) {
        KeymeText(
            text = "서비스 이용약관",
            keymeTextType = KeymeTextType.BODY_5,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .clickable { onClickServicePolicy() },
            color = keyme_white,
        )
        Spacer(modifier = Modifier.size(4.dp))
        KeymeText(
            text = "및",
            keymeTextType = KeymeTextType.BODY_5,
            modifier = Modifier.wrapContentSize(align = Alignment.Center),
            color = white_alpha_50,
        )
        Spacer(modifier = Modifier.size(4.dp))
        KeymeText(
            text = "개인정보 정책",
            keymeTextType = KeymeTextType.BODY_5,
            modifier = Modifier
                .wrapContentSize(align = Alignment.Center)
                .clickable { onCLickPrivacyPolicy() },
            color = keyme_white,
        )
    }
}

private fun signInWithKakao(
    context: Context,
    signInWithKeyme: (String) -> Unit,
) {
    val kakaoSignInCallback: (OAuthToken?, Throwable?) -> Unit = { oAuthToken, error ->
        oAuthToken?.let {
            signInWithKeyme(it.accessToken)
        }
        error?.let {
            when (it is ClientError && it.reason == ClientErrorCause.Cancelled) {
                true -> {
                    /* TODO: Handle error */
                }
                false -> {
                    /* TODO: Handle error */
                }
            }
        }
    }

    when (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        true -> UserApiClient.instance.loginWithKakaoTalk(
            context = context,
            callback = kakaoSignInCallback,
        )
        false -> UserApiClient.instance.loginWithKakaoAccount(
            context = context,
            callback = kakaoSignInCallback,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreview() {
    SignInScreen(
        onClickKakaoSignIn = {},
    )
}
