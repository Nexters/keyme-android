package com.keyme.presentation.editprofile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.KeymeBackgroundAnim
import com.keyme.presentation.designsystem.theme.black_alpha_80
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

    KeymeBackgroundAnim()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = black_alpha_80),
    ) {
        EditProfileScreen(
            onBackClick = onBackClick,
            confirmButtonText = "완료",
            onEditSuccess = onBackClick,
        )
    }
}
