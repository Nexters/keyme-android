package com.keyme.presentation.setting.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object SettingDestination : KeymeNavigationDestination {
    override val route: String = "setting_route"
    override val destination: String = "setting_destination"
}

fun NavGraphBuilder.settingGraph(
    onBackClick: () -> Unit,
) {
    composable(route = SettingDestination.route) {
        SettingRoute(onBackClick, navigateToEditProfile = {})
    }
}

@Composable
fun SettingRoute(onBackClick: () -> Unit, navigateToEditProfile: () -> Unit) {
    SettingScreen(
        onBackClick = onBackClick,
        onProfileChangeClick = navigateToEditProfile,
    )
}
