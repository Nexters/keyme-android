package com.keyme.presentation.keymetestresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.keyme.presentation.navigation.KeymeNavigationDestination
import com.keyme.presentation.utils.clickableRippleEffect

object KeymeTestResultDestination : KeymeNavigationDestination {
    override val route = "take_keyme_test_route"
    override val destination = "take_keyme_test_destination"
}

fun NavGraphBuilder.keymeTestResultGraph(
    onCloseClick: () -> Unit,
) {
    composable(route = KeymeTestResultDestination.route) {
        KeymeTestResultRoute(onCloseClick = onCloseClick)
    }
}

@Composable
fun KeymeTestResultRoute(
    onCloseClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.clickableRippleEffect {
                onCloseClick()
            },
            imageVector = Icons.Outlined.Close, contentDescription = null,
        )
    }
}
