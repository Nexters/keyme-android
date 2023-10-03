package com.keyme.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.theme.keyme_white
import com.keyme.presentation.utils.clickableRippleEffect

@Composable
fun KeymeTitle(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: (() -> Unit)? = null,
    onSettingClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        onBackClick?.let {
            Icon(
                painter = painterResource(id = R.drawable.icon_nav_back),
                contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .clickableRippleEffect(bounded = false) { onBackClick() },
                tint = keyme_white,
            )
        }

        KeymeText(
            text = title,
            keymeTextType = KeymeTextType.BODY_3_SEMIBOLD,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            color = keyme_white,
            textAlign = TextAlign.Center,
        )

        onSettingClick?.let {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(12.dp)
                    .clickableRippleEffect(bounded = false) { onSettingClick() },
                painter = painterResource(id = R.drawable.icon_setting),
                contentDescription = "",
                tint = keyme_white,
            )
        }
    }
}
