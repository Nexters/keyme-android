package com.keyme.app.ui

import android.content.res.Configuration
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.view.drawToBitmap
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.keyme.app.R
import com.keyme.app.navigation.TopLevelDestination
import com.keyme.app.navigation.keymeTopLevelDestinations
import com.keyme.presentation.alarm.ui.AlarmDestination
import com.keyme.presentation.alarm.ui.alarmGraph
import com.keyme.presentation.designsystem.theme.KeymeTheme
import com.keyme.presentation.feed.ui.feedGraph
import com.keyme.presentation.myprofile.ui.myProfileGraph
import com.keyme.presentation.utils.scale

@Composable
fun KeymeApp() {
    val appState = rememberKeymeAppState()

    KeymeTheme {
        Scaffold(
            bottomBar = {
                KeymeBottomBar(
                    currentDestination = appState.currentDestination,
                    onNavigateToDestination = appState::navigate,
                )
            },
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = appState.startDestination.route,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                feedGraph(
                    navigateToAlarm = { appState.navigate(AlarmDestination) },
                    nestedGraphs = {
                        alarmGraph(onBackClick = appState::onBackClick)
                    },
                )
                myProfileGraph()
            }
        }
    }
}

@Composable
fun KeymeBottomBar(
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    NavigationBar {
        keymeTopLevelDestinations.forEach { destination ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == destination.route } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    val iconResId =
                        if (isSelected) destination.selectedIconResId else destination.unselectedIconResId
                    Icon(painter = painterResource(id = iconResId), contentDescription = "")
                },
            )
        }
    }
}
