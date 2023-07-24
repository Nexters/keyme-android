package com.keyme.presentation.feed.ui

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
import com.keyme.presentation.R
import com.keyme.presentation.navigation.KeymeNavigationDestination

object FeedDestination : KeymeNavigationDestination {
    override val route = "feed_route"
    override val destination = "feed_destination"
}

fun NavGraphBuilder.feedGraph(
    navigateToAlarm: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = FeedDestination.route,
        startDestination = FeedDestination.destination,
    ) {
        composable(route = FeedDestination.destination) {
            FeedRoute(navigateToAlarm = navigateToAlarm)
        }
        nestedGraphs()
    }
}

@Composable
fun FeedRoute(
    navigateToAlarm: () -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(16.dp)
                    .clickable { navigateToAlarm() },
                painter = painterResource(id = R.drawable.icon_noti),
                contentDescription = "",
            )
        }
    }
}
