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
import com.keyme.presentation.alarm.ui.AlarmDestination
import com.keyme.presentation.alarm.ui.alarmGraph
import com.keyme.presentation.designsystem.theme.KeymeTheme
import com.keyme.presentation.feed.ui.FeedDestination
import com.keyme.presentation.feed.ui.feedGraph
import com.keyme.presentation.keymetest.KeymeTestDestination
import com.keyme.presentation.keymetest.keymeTestGraph
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import com.keyme.presentation.myprofile.ui.keymeQuestionResultGraph
import com.keyme.presentation.myprofile.ui.myProfileGraph
import com.keyme.presentation.onboarding.OnboardingDestination
import com.keyme.presentation.onboarding.onboardingGraph

@Composable
fun KeymeApp() {
    val appState = rememberKeymeAppState()

    KeymeTheme {
        Scaffold(
            bottomBar = {
                if (appState.currentDestination.isOnBoarding().not()) {
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
                    navigateToOnboardingTest = { appState.navigate(KeymeTestDestination) },
                    navigateToMyDaily = { appState.navigate(FeedDestination) },
                )
                keymeTestGraph(
                    onBackClick = appState::onBackClick,
                    navigateToMyDaily = { appState.navigate(FeedDestination) },
                )
                feedGraph(
                    navigateToAlarm = { appState.navigate(AlarmDestination) },
                    nestedGraphs = {
                        alarmGraph(onBackClick = appState::onBackClick)
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
