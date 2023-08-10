package com.keyme.presentation.nickname

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NicknameRoute(
    onBackClick: () -> Unit,
//    navigateToQuestion: () -> Unit,
) {
    NicknameScreen(onBackClick)
}

@Composable
fun NicknameScreen(
    onBackClick: () -> Unit,
//    navigateToQuestion: () -> Unit,
) {
    val contentHorizontalPadding = 16

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Nickname")
    }
}

@Composable
@Preview(showBackground = true)
fun NicknameScreenPreview() {
    NicknameScreen(
        onBackClick = {},
//        navigateToQuestion = {},
    )
}
