package com.keyme.presentation.nickname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NicknameRoute(
    onBackClick: () -> Unit,
//    navigateToKeymeTest: () -> Unit,
) {
    NicknameScreen(onBackClick)
}

@Composable
fun NicknameScreen(
    onBackClick: () -> Unit,
//    navigateToKeymeTest: () -> Unit,
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
//        navigateToKeymeTest = {},
    )
}
