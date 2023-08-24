package com.keyme.presentation.onboarding.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextButton
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.black_alpha_60
import com.keyme.presentation.designsystem.theme.keyme_white
import com.keyme.presentation.onboarding.fadingAnimateFloatAsState

@Composable
fun Guide01Screen(
    isVisible: Boolean,
    onClickNextButton: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_guide_01))
    val progress by animateLottieCompositionAsState(composition)
    var isAnimationFinished by remember { mutableStateOf(false) }
    isAnimationFinished = progress == 1.0f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(black_alpha_60)
            .alpha(fadingAnimateFloatAsState(isAnimationFinished = isVisible).value),
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .align(Alignment.Center),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop,
        )

        KeymeText(
            text = "친구들이 생각하는\n나의 성격을 발견하고",
            keymeTextType = KeymeTextType.HEADING_1,
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = 18.dp,
                    top = 74.dp,
                ),
            color = keyme_white,
        )

        KeymeTextButton(
            text = "다음",
            onClick = onClickNextButton,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(bottom = 54.dp)
                .align(Alignment.BottomCenter)
                .alpha(fadingAnimateFloatAsState(isAnimationFinished = isAnimationFinished).value),
            enabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Guide01ScreenPreview() {
    Guide01Screen(
        isVisible = true,
        onClickNextButton = {},
    )
}
