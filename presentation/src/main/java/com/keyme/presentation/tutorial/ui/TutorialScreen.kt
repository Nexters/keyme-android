package com.keyme.presentation.tutorial.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.keyme_white

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialScreen(
    onFinish: () -> Unit,
) {
    BackHandler {
        // disable
    }

    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pageCount = 1,
            state = pagerState,
        ) {


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = 1,
                activeColor = Color.White,
                inactiveColor = Color(0x4DFFFFFF),
                indicatorWidth = 6.dp,
                indicatorHeight = 6.dp,
            )
        }

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 25.dp)
                .height(60.dp),
            shape = RoundedCornerShape(size = 16.dp),
            colors = ButtonDefaults.textButtonColors(backgroundColor = keyme_white),
            onClick = {
                onFinish()
            },
        ) {
            KeymeText(
                text = "시작하기",
                keymeTextType = KeymeTextType.BODY_2,
                color = Color.Black,
            )
        }
    }
}
