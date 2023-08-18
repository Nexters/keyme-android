package com.keyme.presentation.keymetestresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.keyme.presentation.utils.clickableRippleEffect

@Composable
fun KeymeTestResultScreen(
    onCloseClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.clickableRippleEffect {
                onCloseClick()
            },
            imageVector = Icons.Outlined.Close,
            contentDescription = null,
        )
    }
}
