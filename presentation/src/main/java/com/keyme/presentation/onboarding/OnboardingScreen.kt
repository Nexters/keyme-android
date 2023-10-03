package com.keyme.presentation.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.theme.black_alpha_60
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.editprofile.ui.EditProfileScreen
import com.keyme.presentation.onboarding.guide.Guide01Screen
import com.keyme.presentation.onboarding.guide.Guide02Screen
import com.keyme.presentation.onboarding.guide.Guide03Screen
import com.keyme.presentation.onboarding.guide.Guide04Screen
import com.keyme.presentation.onboarding.signin.SignInScreen

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToOnboardingKeymeTest: (testId: Int) -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    val onBoardingPageUiState by viewModel.onBoardingPageUiState.collectAsStateWithLifecycle()

    OnboardingScreen(
        onBoardingPageUiState = onBoardingPageUiState,
        navigateToOnboardingKeymeTest = navigateToOnboardingKeymeTest,
        navigateToMyDaily = navigateToMyDaily,
        signInWithKeyme = viewModel::signInWithKeyme,
        onPageIndexChanged = viewModel::onPageIndexChange,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onBoardingPageUiState: OnBoardingPageUiState,
    navigateToOnboardingKeymeTest: (testId: Int) -> Unit,
    navigateToMyDaily: () -> Unit,
    signInWithKeyme: (String) -> Unit,
    onPageIndexChanged: (Int) -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_signin_background))

    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(key1 = onBoardingPageUiState) {
        pagerState.scrollToPage(onBoardingPageUiState.currentPage.ordinal)
    }

    LaunchedEffect(key1 = onBoardingPageUiState) {
        if (onBoardingPageUiState.currentPage == OnboardingStepsEnum.MY_DAILY) navigateToMyDaily()
    }

    when (pagerState.currentPage) {
        0 -> BackHandler(enabled = true) { /*TODO*/ }
        else -> BackHandler(enabled = true) {
            onPageIndexChanged(pagerState.currentPage - 1)
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
            pageCount = onBoardingPageUiState.pageSize,
            state = pagerState,
            userScrollEnabled = false,
        ) {
            val isVisible = it == pagerState.currentPage

            when (OnboardingStepsEnum.onboardingSteps[it]) {
                OnboardingStepsEnum.KAKAO_SIGN_IN -> SignInScreen(signInWithKeyme)

                OnboardingStepsEnum.NICKNAME -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(black_alpha_60)
                            .alpha(fadingAnimateFloatAsState(isAnimationFinished = isVisible).value),
                    ) {
                        EditProfileScreen(
                            onBackClick = {
                                onPageIndexChanged(pagerState.currentPage - 1)
                            },
                            confirmButtonText = "다음",
                            onEditSuccess = {

                            },
                        )
                    }
                }

                OnboardingStepsEnum.GUIDE_01 -> Guide01Screen(
                    isVisible = isVisible,
                    onClickNextButton = {
                        onPageIndexChanged(pagerState.currentPage + 1)
                    },
                )

                OnboardingStepsEnum.GUIDE_02 -> Guide02Screen(
                    isVisible = isVisible,
                    onClickNextButton = {
                        onPageIndexChanged(pagerState.currentPage + 1)
                    },
                )

                OnboardingStepsEnum.GUIDE_03 -> Guide03Screen(
                    isVisible = isVisible,
                    onClickNextButton = {
                        onPageIndexChanged(pagerState.currentPage + 1)
                    },
                )

                OnboardingStepsEnum.GUIDE_04 -> Guide04Screen(
                    isVisible = isVisible,
                    navigateToOnboardingKeymeTest = navigateToOnboardingKeymeTest,
                )

                else -> {}
            }
        }
    }
}

@Composable
fun fadingAnimateFloatAsState(isAnimationFinished: Boolean): State<Float> {
    return animateFloatAsState(
        targetValue = if (isAnimationFinished) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "",
    )
}

@Composable
fun colorAnimateFloatAsState(isVisible: Boolean): State<Color> {
    return animateColorAsState(
        targetValue = if (isVisible) keyme_black else black_alpha_60,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )
}

@Composable
@Preview(showBackground = true)
fun OnboardingScreenPreview() {
    SignInScreen(
        signInWithKeyme = {},
    )
}
