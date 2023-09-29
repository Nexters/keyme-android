package com.keyme.presentation.setting.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.setting.SettingViewModel

object SettingDestination : KeymeNavigationDestination {
    override val route: String = "setting_route"
    override val destination: String = "setting_destination"
}

fun NavGraphBuilder.settingGraph(
    onBackClick: () -> Unit,
    navigateToEditProfile: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = SettingDestination.route,
        startDestination = SettingDestination.destination,
    ) {
        composable(route = SettingDestination.destination) {
            SettingRoute(onBackClick = onBackClick, navigateToEditProfile = navigateToEditProfile)
        }
        nestedGraphs()
    }

}

@Composable
fun SettingRoute(
    settingViewModel: SettingViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToEditProfile: () -> Unit,
) {
    SettingScreen(
        onBackClick = onBackClick,
        onProfileChangeClick = navigateToEditProfile,
        onLogOutClick = settingViewModel::logOut,
        onWithdrawClick = settingViewModel::withdraw
    )
}
