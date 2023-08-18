package com.keyme.presentation.onboarding.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.keyme.presentation.R
import com.keyme.presentation.onboarding.colorAnimateFloatAsState

@Composable
fun Guide03Screen(
    isVisible: Boolean,
    onClickNextButton: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_guide_03))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorAnimateFloatAsState(isVisible = isVisible).value),
        )
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .align(Alignment.Center),
            contentScale = ContentScale.Crop,
        )
        if (progress == 1.0f) onClickNextButton()
    }
}

@Composable
@Preview(showBackground = true)
fun Guide03ScreenPreview() {
    Guide03Screen(
        isVisible = true,
        onClickNextButton = {},
    )
}
