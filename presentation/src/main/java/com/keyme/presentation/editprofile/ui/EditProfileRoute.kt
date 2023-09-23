package com.keyme.presentation.editprofile.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination

object EditProfileDestination : KeymeNavigationDestination {
    override val route: String = "EditProfile_route"
    override val destination: String = "EditProfile_destination"
}

fun NavGraphBuilder.editProfileGraph(
    onBackClick: () -> Unit,
) {
    composable(route = EditProfileDestination.route) {
        EditProfileRoute(onBackClick)
    }
}

@Composable
fun EditProfileRoute(onBackClick: () -> Unit) {
    EditProfileScreen(
        onBackClick = onBackClick,
        confirmButtonText = "완료",
    )
}
