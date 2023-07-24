package com.keyme.presentation.myprofile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object MyProfileDestination : KeymeNavigationDestination {
    override val route: String = "myProfile_route"
    override val destination: String = "myProfile_destination"
}

fun NavGraphBuilder.myProfileGraph() {
    composable(route = MyProfileDestination.route) {
        MyProfileRoute()
    }
}

@Composable
fun MyProfileRoute() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "MyProfile", style = MaterialTheme.typography.bodyLarge)
    }
}
