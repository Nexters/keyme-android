package com.keyme.presentation.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keyme.presentation.navigation.KeymeNavigationDestination

object OnboardingDestination : KeymeNavigationDestination {
    override val route = "onboarding_route"
    override val destination = "onboarding_destination"
}

fun NavGraphBuilder.onboardingGraph(
    navigateToOnboardingTest: () -> Unit,
    navigateToMyDaily: () -> Unit,
) {
    navigation(
        route = OnboardingDestination.route,
        startDestination = OnboardingDestination.destination,
    ) {
        composable(route = OnboardingDestination.destination) {
            OnboardingRoute(
                navigateToOnboardingTest = navigateToOnboardingTest,
                navigateToMyDaily = navigateToMyDaily,
            )
        }
    }
}
