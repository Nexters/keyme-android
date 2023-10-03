package com.keyme.presentation.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.designsystem.theme.keyme_white

@Composable
fun KeymeTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(size = 16.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = keyme_white,
            disabledContainerColor = Color(0x0DFFFFFF),
        ),
        contentPadding = PaddingValues(),
    ) {
        KeymeText(
            text = text,
            keymeTextType = KeymeTextType.BODY_2,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 18.dp),
            color = keyme_black,
        )
    }
}
