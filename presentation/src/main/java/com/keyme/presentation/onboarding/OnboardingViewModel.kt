package com.keyme.presentation.onboarding

import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    private val _localOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
    val localOnboardingState: StateFlow<Int> = _localOnboardingState.asStateFlow()

    private val _remoteOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
    val remoteOnboardingState: StateFlow<Int> = _remoteOnboardingState.asStateFlow()

    fun signInWithKeyme(
        token: String,
    ) {
        apiCall(apiRequest = { signInUseCase.invoke(token) }) {
            _remoteOnboardingState.emit(
                when {
                    it.nickname.isNullOrBlank() -> OnboardingStepsEnum.NICKNAME
                    it.onboardingTestResultId == null -> OnboardingStepsEnum.GUIDE_01
                    else -> OnboardingStepsEnum.MY_DAILY
                }.ordinal,
            )
        }
    }
}
