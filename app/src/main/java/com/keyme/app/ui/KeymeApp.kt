package com.keyme.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.keyme.app.navigation.TopLevelDestination
import com.keyme.app.navigation.keymeTopLevelDestinations
import com.keyme.presentation.dailykeymetest.DailyKeymeTestDestination
import com.keyme.presentation.dailykeymetest.dailyKeymeTestGraph
import com.keyme.presentation.designsystem.theme.KeymeTheme
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import com.keyme.presentation.myprofile.ui.keymeQuestionResultGraph
import com.keyme.presentation.myprofile.ui.myProfileGraph
import com.keyme.presentation.onboarding.OnboardingDestination
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
                    navigateToMyDaily = {
                        appState.onBackClick()
                        appState.navigate(DailyKeymeTestDestination)
                    },
                    nestedGraphs = {
                        takeKeymeTestGraph(
                            onBackClick = appState::onBackClick,
                            onTestSolved = {
                                appState.onBackClick()
                                appState.navigate(DailyKeymeTestDestination)
                            },
                        )
                    },
                )
                dailyKeymeTestGraph(
                    navigateToTakeKeymeTest = { appState.navigate(TakeKeymeTestDestination, it) },
                    nestedGraphs = {
                        takeKeymeTestGraph(
                            onBackClick = appState::onBackClick,
                            onTestSolved = appState::onBackClick,
                        )
                    },
                )
                // takeKeymeTestGraph -> 문제 풀이, 결과 확인

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

private fun NavDestination?.isOnBoarding(): Boolean {
    return this?.hierarchy?.any { it.route == OnboardingDestination.route } == true
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
