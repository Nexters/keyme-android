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
import com.keyme.presentation.keymetest.KeymeTestDestination
import com.keyme.presentation.keymetest.keymeTestGraph
import com.keyme.presentation.keymetestresult.KeymeTestResultDestination
import com.keyme.presentation.keymetestresult.keymeTestResultGraph
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
                    navigateToOnboardingTest = { appState.navigate(KeymeTestDestination) },
                    navigateToMyDaily = {
                        appState.onBackClick()
                        appState.navigate(DailyKeymeTestDestination)
                    },
                )
                // NOTE: 문제 풀이 화면인지? 확실하지 않아서 문제 풀이 화면 - takeKeymeTest 로 구현함
                // 확인 후 제거 필요
                keymeTestGraph(
                    onBackClick = appState::onBackClick,
                    navigateToMyDaily = { appState.navigate(DailyKeymeTestDestination) },
                )
                dailyKeymeTestGraph(
                    navigateToTakeKeymeTest = { appState.navigate(TakeKeymeTestDestination, it) },
                    nestedGraphs = {
                        takeKeymeTestGraph(
                            navigateToKeymeTestResult = {
                                appState.onBackClick()
                                appState.navigate(KeymeTestResultDestination, it)
                            },
                            onBackClick = appState::onBackClick,
                        )

                        keymeTestResultGraph(onCloseClick = appState::onBackClick)
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
