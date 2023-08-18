package com.keyme.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.theme.black_alpha_60
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.onboarding.guide.Guide01Screen
import com.keyme.presentation.onboarding.guide.Guide02Screen
import com.keyme.presentation.onboarding.guide.Guide03Screen
import com.keyme.presentation.onboarding.guide.Guide04Screen
import com.keyme.presentation.onboarding.nickname.NicknameScreen
import com.keyme.presentation.onboarding.signin.SignInScreen
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(
    navigateToOnboardingKeymeTest: (testId: Int) -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    OnboardingScreen(
        navigateToOnboardingKeymeTest = navigateToOnboardingKeymeTest,
        navigateToMyDaily = navigateToMyDaily,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToOnboardingKeymeTest: (testId: Int) -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_signin_background))
    val localOnboardingState by viewModel.userAuthState.collectAsState()

    val pagerState = rememberPagerState(initialPage = 0)
    val onboardingSteps = listOf(
        OnboardingStepsEnum.KAKAO_SIGN_IN,
        OnboardingStepsEnum.NICKNAME,
        OnboardingStepsEnum.GUIDE_01,
        OnboardingStepsEnum.GUIDE_02,
        OnboardingStepsEnum.GUIDE_03,
        OnboardingStepsEnum.GUIDE_04,
    )

    LaunchedEffect(localOnboardingState) {
//        TODO: 토큰만 있는 상태에서 메인으로 넘기기 위해 주석처리
        localOnboardingState.let {
//            when {
//                it?.accessToken == null -> pagerState.scrollToPage(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
//                it.nickname == null -> pagerState.scrollToPage(OnboardingStepsEnum.NICKNAME.ordinal)
//                it.onboardingTestResultId == null -> pagerState.scrollToPage(OnboardingStepsEnum.GUIDE_01.ordinal)
//                else -> navigateToMyDaily.invoke()
//            }
//            TODO: 토큰만 있는 상태에서 메인으로 넘기는 부분
            if (it?.accessToken != null) navigateToMyDaily.invoke()
        }
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
            val isVisible = it == pagerState.currentPage

            when (onboardingSteps[it]) {
                OnboardingStepsEnum.KAKAO_SIGN_IN -> SignInScreen(
                    signInWithKeyme = viewModel::signInWithKeyme,
                )
                OnboardingStepsEnum.NICKNAME -> NicknameScreen(
                    isVisible = isVisible,
                    onBackClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_01 -> Guide01Screen(
                    isVisible = isVisible,
                    getOnboardingKeymeTestId = viewModel::getOnboardingKeymeTest,
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_02 -> Guide02Screen(
                    isVisible = isVisible,
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_03 -> Guide03Screen(
                    isVisible = isVisible,
                    onClickNextButton = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
                OnboardingStepsEnum.GUIDE_04 -> Guide04Screen(
                    isVisible = isVisible,
                    navigateToOnboardingKeymeTest = navigateToOnboardingKeymeTest,
                )
                OnboardingStepsEnum.MY_DAILY -> navigateToMyDaily.invoke()
            }
        }
    }
}

@Composable
fun fadingAnimateFloatAsState(isAnimationFinished: Boolean): State<Float> {
    return animateFloatAsState(
        targetValue = if (isAnimationFinished) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
    )
}

@Composable
fun colorAnimateFloatAsState(isVisible: Boolean): State<Color> {
    return animateColorAsState(
        targetValue = if (isVisible) keyme_black else black_alpha_60,
        animationSpec = tween(durationMillis = 1000),
    )
}

@Composable
@Preview(showBackground = true)
fun OnboardingScreenPreview() {
    SignInScreen(
        signInWithKeyme = {},
    )
}
