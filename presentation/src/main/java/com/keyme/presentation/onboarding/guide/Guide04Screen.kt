package com.keyme.presentation.onboarding.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_black

@Composable
fun Guide04Screen(
    onClickNextButton: () -> Unit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_guide_04))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(keyme_black),
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable {
                    if (progress == 1.0f) onClickNextButton()
                },
//            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop,
        )
        KeymeText(
            text = "환영해요\n이제 문제를 풀어볼까요?",
            keymeTextType = KeymeTextType.HEADING_1,
            modifier = Modifier
                .wrapContentSize()
                .padding(
                    start = 18.dp,
                    top = 74.dp,
                ),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Guide04ScreenPreview() {
    Guide04Screen(
        onClickNextButton = {},
    )
}
