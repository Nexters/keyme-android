package com.keyme.presentation.onboarding

enum class OnboardingStepsEnum {
    KAKAO_SIGN_IN, NICKNAME, GUIDE_01, GUIDE_02, GUIDE_03, GUIDE_04, MY_DAILY,
    ;

    companion object {
        val onboardingSteps = listOf(
            KAKAO_SIGN_IN,
            NICKNAME,
            GUIDE_01,
            GUIDE_02,
            GUIDE_03,
            GUIDE_04,
            MY_DAILY,
        )
    }
}
