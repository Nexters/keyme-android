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
    navigateToOnboardingKeymeTest: (testId: Int) -> Unit,
    navigateToMyDaily: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = OnboardingDestination.route,
        startDestination = OnboardingDestination.destination,
    ) {
        composable(route = OnboardingDestination.destination) {
            OnboardingRoute(
                navigateToOnboardingKeymeTest = navigateToOnboardingKeymeTest,
                navigateToMyDaily = navigateToMyDaily,
            )
        }
        nestedGraphs()
    }
}
