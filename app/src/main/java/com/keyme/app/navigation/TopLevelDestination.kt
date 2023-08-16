package com.keyme.app.navigation

import androidx.annotation.DrawableRes
import com.keyme.presentation.dailykeymetest.DailyKeymeTestDestination
import com.keyme.presentation.myprofile.ui.MyProfileDestination
import com.keyme.presentation.navigation.KeymeNavigationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val selectedIconResId: Int,
    @DrawableRes val unselectedIconResId: Int,
) : KeymeNavigationDestination

val keymeTopLevelDestinations = listOf(
    TopLevelDestination(
        route = DailyKeymeTestDestination.route,
        destination = DailyKeymeTestDestination.destination,
        selectedIconResId = com.keyme.presentation.R.drawable.icon_tab_feed,
        unselectedIconResId = com.keyme.presentation.R.drawable.icon_tab_feed_unselected,
    ),
    TopLevelDestination(
        route = MyProfileDestination.route,
        destination = MyProfileDestination.destination,
        selectedIconResId = com.keyme.presentation.R.drawable.icon_tab_my_profile,
        unselectedIconResId = com.keyme.presentation.R.drawable.icon_tab_my_profile_unselected,
    ),
)
