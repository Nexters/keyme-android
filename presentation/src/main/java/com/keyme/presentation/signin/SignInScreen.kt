package com.keyme.presentation.signin

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.keyme.presentation.signin.enums.SignInStateEnum

@Composable
fun SignInRoute(
    navigateToNickname: () -> Unit,
//    navigateToHome: () -> Unit,
//    viewModel: SignInViewModel = hiltViewModel(),
) {
    SignInScreen()
}

@Composable
fun SignInScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "SignIn")
    }
}

@Composable
@Preview(showBackground = true)
fun SignInScreenPreview() {
    SignInScreen()
}
