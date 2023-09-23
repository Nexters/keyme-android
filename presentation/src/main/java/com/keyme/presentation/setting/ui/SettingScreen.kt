package com.keyme.presentation.setting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keyme.presentation.KeymeBackgroundAnim
import com.keyme.presentation.R
import com.keyme.presentation.designsystem.component.KeymeText
import com.keyme.presentation.designsystem.component.KeymeTextType
import com.keyme.presentation.designsystem.theme.black_alpha_60
import com.keyme.presentation.designsystem.theme.black_alpha_80
import com.keyme.presentation.designsystem.theme.keyme_black
import com.keyme.presentation.setting.SettingViewModel
import com.keyme.presentation.utils.clickableRippleEffect

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onProfileChangeClick: () -> Unit,
) {
    KeymeBackgroundAnim()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black_alpha_80),
    ) {
        var showLogOutAlert by remember { mutableStateOf(false) }
        var showWithdrawAlert by remember { mutableStateOf(false) }

        SettingTitleBar(onBackClick = onBackClick)
        Spacer(modifier = Modifier.height(40.dp))
        SettingContent(
            onProfileChangeClick = onProfileChangeClick,
            onLogOutClick = { showLogOutAlert = true },
            onWithdrawClick = { showWithdrawAlert = true },
        )

        if (showLogOutAlert) {
            AlertDialog(
                onDismissRequest = { showLogOutAlert = false },
                containerColor = Color(0xff232323),
                text = {
                    KeymeText(
                        text = "로그아웃 하시겠어요?",
                        keymeTextType = KeymeTextType.BODY_4,
                        color = Color.White,
                    )
                },
                dismissButton = {
                    TextButton(onClick = { showLogOutAlert = false }) {
                        KeymeText(
                            text = "아니요",
                            keymeTextType = KeymeTextType.CAPTION_1,
                            color = Color(0xFF747474),
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showLogOutAlert = false
                            settingViewModel.logOut()
                        },
                    ) {
                        KeymeText(
                            text = "예",
                            keymeTextType = KeymeTextType.CAPTION_1,
                            color = Color(0xFF747474),
                        )
                    }
                },
            )
        }

        if (showWithdrawAlert) {
            AlertDialog(
                onDismissRequest = { showWithdrawAlert = false },
                containerColor = Color(0xff232323),
                text = {
                    KeymeText(
                        text = "탈퇴 시 모든 정보가 삭제됩니다. 정말 탈퇴하시겠어요?",
                        keymeTextType = KeymeTextType.BODY_4,
                        color = Color.White,
                    )
                },
                dismissButton = {
                    TextButton(onClick = { showWithdrawAlert = false }) {
                        KeymeText(
                            text = "아니요",
                            keymeTextType = KeymeTextType.CAPTION_1,
                            color = Color(0xFF747474),
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showWithdrawAlert = false
                            settingViewModel.withdraw()
                        },
                    ) {
                        KeymeText(
                            text = "예",
                            keymeTextType = KeymeTextType.CAPTION_1,
                            color = Color(0xFF747474),
                        )
                    }
                },
            )
        }
    }
}

@Composable
private fun SettingTitleBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .clickableRippleEffect(bounded = false) { onBackClick() },
            painter = painterResource(id = R.drawable.icon_nav_back),
            contentDescription = "",
            tint = Color.White,
        )

        KeymeText(
            modifier = Modifier.align(Alignment.Center),
            text = "설정",
            keymeTextType = KeymeTextType.BODY_3_REGULAR,
            color = Color.White,
        )
    }
}

@Composable
private fun SettingContent(
    onProfileChangeClick: () -> Unit,
    onLogOutClick: () -> Unit,
    onWithdrawClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
    ) {
        KeymeText(text = "개인 정보", keymeTextType = KeymeTextType.BODY_4, color = Color(0xFF747474))

        Spacer(modifier = Modifier.height(24.dp))

        KeymeText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onProfileChangeClick()
                    },
                )
                .padding(vertical = 12.dp),
            text = "프로필 변경",
            keymeTextType = KeymeTextType.BODY_2,
            color = Color.White,
        )

        KeymeText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onLogOutClick()
                    },
                )
                .padding(vertical = 12.dp),
            text = "로그아웃",
            keymeTextType = KeymeTextType.BODY_2,
            color = Color.White,
        )

        KeymeText(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onWithdrawClick()
                    },
                )
                .padding(vertical = 12.dp),
            text = "서비스 탈퇴",
            keymeTextType = KeymeTextType.BODY_2,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(18.dp))

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(0x1AFFFFFF))

        Spacer(modifier = Modifier.height(30.dp))

        KeymeText(text = "개인 정보", keymeTextType = KeymeTextType.BODY_4, color = Color(0xFF747474))

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            KeymeText(
                modifier = Modifier.weight(1f),
                text = "푸시알림",
                keymeTextType = KeymeTextType.BODY_2,
                color = Color.White,
            )

            Switch(
                checked = true,
                onCheckedChange = { },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF89B5F6),
                    uncheckedTrackColor = keyme_black,
                    uncheckedBorderColor = Color(0xFF89B5F6),
                ),
            )
        }
    }
}
