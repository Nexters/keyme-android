package com.keyme.presentation.keymetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType

@Composable
fun KeymeTestRoute(
    onBackClick: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    KeymeTestScreen(
        onBackClick = onBackClick,
        navigateToMyDaily = navigateToMyDaily,
    )
}

@Composable
fun KeymeTestScreen(
    onBackClick: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    val contentHorizontalPadding = 16

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        KeymeText(
            text = "KeymeTest",
            keymeTextType = KeymeTextType.HEADING_1,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun KeymeTestScreenPreview() {
    KeymeTestScreen(
        onBackClick = {},
        navigateToMyDaily = {},
    )
}
