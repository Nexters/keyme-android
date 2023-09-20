package com.keyme.app.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.keyme.app.navigation.TopLevelDestination
import com.keyme.app.navigation.keymeTopLevelDestinations
import com.keyme.presentation.dailykeymetest.dailyKeymeTestGraph
import com.keyme.presentation.designsystem.theme.KeymeTheme
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import com.keyme.presentation.myprofile.ui.keymeQuestionResultGraph
import com.keyme.presentation.myprofile.ui.myProfileGraph
import com.keyme.presentation.onboarding.onboardingGraph
import com.keyme.presentation.takekeymetest.TakeKeymeTestDestination
import com.keyme.presentation.takekeymetest.takeKeymeTestGraph

@Composable
fun KeymeApp() {
    val appState = rememberKeymeAppState()

    KeymeTheme {
        Scaffold(
            bottomBar = {
                if (appState.showBottomBar) {
                    KeymeBottomBar(
                        currentDestination = appState.currentDestination,
                        onNavigateToDestination = appState::navigate,
                    )
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = appState.startDestination.route,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                onboardingGraph(
                    navigateToOnboardingKeymeTest = { appState.navigate(TakeKeymeTestDestination, it) },
                    navigateToMain = {
                        // todo 코드 정리 필요
                        appState.startDestination = keymeTopLevelDestinations[0]
                        navigateToMain(appState)
                    },
                    nestedGraphs = {
                        takeKeymeTestGraph(
                            onBackClick = appState::onBackClick,
                            onTestSolved = {
                                navigateToMain(appState)
                            },
                        )
                    },
                )

                dailyKeymeTestGraph(
                    navigateToTakeKeymeTest = { appState.navigate(TakeKeymeTestDestination, it) },
                    navigateToQuestionResult = { appState.navigate(KeymeQuestionResultDestination, it.questionId) },
                    nestedGraphs = {
                        keymeQuestionResultGraph(onBackClick = appState::onBackClick)
                        takeKeymeTestGraph(
                            onBackClick = appState::onBackClick,
                            onTestSolved = appState::onBackClick,
                        )
                    },
                )

                myProfileGraph(
                    navigateToQuestionResult = { question ->
                        appState.navigate(
                            KeymeQuestionResultDestination,
                            question.questionId,
                        )
                    },
                    nestedGraphs = {
                        keymeQuestionResultGraph(onBackClick = appState::onBackClick)
                    },
                )
            }
        }
    }
}

private fun navigateToMain(appState: KeymeAppState) {
    appState.navigate(keymeTopLevelDestinations[0])
}

@Composable
fun KeymeBottomBar(
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.border(width = 1.dp, color = Color(0xFF363636)),
        containerColor = Color(0x80232323),
        tonalElevation = 4.dp,
    ) {
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
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
            )
        }
    }
}
