package com.keyme.presentation.dailykeymetest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.domain.entity.member.Member
import com.keyme.presentation.R
import com.keyme.presentation.navigation.KeymeNavigationDestination

object DailyKeymeTestDestination : KeymeNavigationDestination {
    override val route = "daily_keyme_test_route"
    override val destination = "daily_keyme_test_destination"
}

fun NavGraphBuilder.dailyKeymeTestGraph(
    navigateToAlarm: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = DailyKeymeTestDestination.route,
        startDestination = DailyKeymeTestDestination.destination,
    ) {
        composable(route = DailyKeymeTestDestination.destination) {
            DailyKeymeTestRoute(navigateToAlarm = navigateToAlarm)
        }
        nestedGraphs()
    }
}

@Composable
fun DailyKeymeTestRoute(
    navigateToAlarm: () -> Unit,
) {

    DailyKeymeTestScreen(
        myCharacter = Member(
            friendCode = "",
            id = 0,
            nickname = "",
            profileImage = "",
            profileThumbnail = "",
        ),
    )
}
