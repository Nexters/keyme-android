package com.keyme.presentation.onboarding

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.keyme.presentation.R
import com.keyme.presentation.onboarding.guide.Guide01Screen
import com.keyme.presentation.onboarding.guide.Guide02Screen
import com.keyme.presentation.onboarding.guide.Guide03Screen
import com.keyme.presentation.onboarding.guide.Guide04Screen
import com.keyme.presentation.onboarding.nickname.NicknameScreen
import com.keyme.presentation.onboarding.signin.SignInScreen
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(
    navigateToOnboardingTest: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    OnboardingScreen(
        navigateToOnboardingTest = navigateToOnboardingTest,
        navigateToMyDaily = navigateToMyDaily,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToOnboardingTest: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_signin_background))
    val remoteOnboardingState by viewModel.remoteOnboardingState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(initialPage = 0)
    val onboardingSteps = listOf(
        OnboardingStepsEnum.KAKAO_SIGN_IN,
        OnboardingStepsEnum.NICKNAME,
        OnboardingStepsEnum.GUIDE_01,
        OnboardingStepsEnum.GUIDE_02,
        OnboardingStepsEnum.GUIDE_03,
        OnboardingStepsEnum.GUIDE_04,
    )

    LaunchedEffect(viewModel) { // Todo: Set local data value.
        viewModel.localOnboardingState.collect { newPage ->
            when (newPage == OnboardingStepsEnum.MY_DAILY.ordinal) {
                true -> navigateToMyDaily.invoke()
                false -> pagerState.scrollToPage(newPage)
            }
        }
    }

    LaunchedEffect(remoteOnboardingState) {
        pagerState.scrollToPage(remoteOnboardingState)
    }

    when (pagerState.currentPage) {
        0 -> BackHandler(enabled = true) { /*TODO*/ }
        else -> BackHandler(enabled = true) {
            coroutineScope.launch {
                pagerState.scrollToPage(pagerState.currentPage - 1)
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    when (pagerState.currentPage == OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal) {
                        true -> 0.dp
                        false -> 60.dp
                    },
                ),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop,
        )

        HorizontalPager(
            pageCount = onboardingSteps.size,
            state = pagerState,
            userScrollEnabled = false,
        ) {
            when (onboardingSteps[it]) {
                OnboardingStepsEnum.KAKAO_SIGN_IN -> SignInScreen(
                    onClickKakaoSignIn = {
                        signInWithKakao(context, viewModel::signInWithKeyme)
                    },
                )
                OnboardingStepsEnum.NICKNAME -> NicknameScreen(
                    onBackClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_01 -> Guide01Screen(
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_02 -> Guide02Screen(
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_03 -> Guide03Screen(
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_04 -> Guide04Screen(
                    onClickNextButton = navigateToOnboardingTest,
                )
                OnboardingStepsEnum.MY_DAILY -> navigateToMyDaily.invoke()
            }
        }
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
fun OnboardingScreenPreview() {
    SignInScreen(
        onClickKakaoSignIn = {},
    )
}
