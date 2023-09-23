package com.keyme.presentation.setting.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

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
            SettingRoute(onBackClick, navigateToEditProfile = navigateToEditProfile)
        }
        nestedGraphs()
    }

}

@Composable
fun SettingRoute(onBackClick: () -> Unit, navigateToEditProfile: () -> Unit) {
    SettingScreen(
        onBackClick = onBackClick,
        onProfileChangeClick = navigateToEditProfile,
    )
}
