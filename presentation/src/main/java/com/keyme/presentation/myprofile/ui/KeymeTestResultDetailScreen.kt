package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.utils.clickableRippleEffect
import com.keyme.presentation.utils.textDp
import timber.log.Timber

@Composable
fun KeymeTestResultDetailScreen(onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .padding(top = 20.dp, start = 16.dp)
                .clickableRippleEffect(bounded = false) { onBackClick() },
            painter = painterResource(id = R.drawable.icon_nav_back),
            contentDescription = "",
            tint = Color.White,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KeymeTestInfo()

            Box(
                modifier = Modifier
                    .padding(top = 12.dp, start = 30.dp, end = 30.dp, bottom = 30.dp)
                    .weight(1f)
                    .aspectRatio(1f)
                    .shadow(
                        elevation = 20.dp,
                        spotColor = Color(0x00000000),
                        ambientColor = Color(0x00000000),
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0x4DFFFFFF),
                        shape = RoundedCornerShape(size = 320.00003.dp),
                    )
                    .background(color = Color(0x4DFFFFFF), shape = RoundedCornerShape(size = 320.00003.dp)),
            )

            var bottomWeightValue by remember {
                mutableStateOf(1f)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF232323),
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    )
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .weight(bottomWeightValue),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .pointerInput(Unit) {
                            detectVerticalDragGestures { _, dragAmount ->
                                bottomWeightValue = (bottomWeightValue - dragAmount / 200).coerceIn(1f, 5f)
                                Timber.d("bottomWeightValue: $bottomWeightValue")
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .background(color = Color(0x4DFFFFFF), shape = RoundedCornerShape(size = 2.dp)),
                    )
                }

                Text(
                    text = "키미미미미미님의 애정 표현정도는?",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    ),
                )
                Text(
                    text = "응답자 수 16명",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0x99FFFFFF),
                    ),
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(100) {
                        Item()
                    }
                }
            }
        }
    }
}

@Composable
private fun Item() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 20.dp),
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = "5점")
        Text(modifier = Modifier.align(Alignment.CenterEnd), text = "2시간전")
    }
}

@Composable
private fun KeymeTestInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 16.dp),
                )
                .background(color = Color(0x33FFFFFF), shape = RoundedCornerShape(size = 16.dp))
                .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp),
            text = "표현력",
        )

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "4.0",
                fontFamily = panchang,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 40.textDp(),
                color = Color.White.copy(alpha = 0.6f),
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 10.dp),
                text = "점",
                fontSize = 12.textDp(),
                fontWeight = FontWeight.Medium,
                color = Color.White.copy(alpha = 0.6f),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
private fun KeymeTestDetailScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = keyme_black),
    ) {
        KeymeTestResultDetailScreen(
            onBackClick = {},
        )
    }
}
