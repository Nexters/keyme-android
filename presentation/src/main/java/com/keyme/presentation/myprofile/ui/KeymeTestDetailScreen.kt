package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.theme.panchang
import com.keyme.presentation.utils.clickableRippleEffect
import com.keyme.presentation.utils.textDp

@Composable
fun KeymeTestDetailScreen(onBackClick: () -> Unit) {
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
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            KeymeTestInfo()
        }
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
                .border(width = 0.5.dp, color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
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
    KeymeTestDetailScreen(
        onBackClick = {},
    )
}
