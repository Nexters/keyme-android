package com.keyme.presentation.onboarding

import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.InsertPushTokenUseCase
import com.keyme.domain.usecase.SetPushTokenSavedStateUseCase
import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.utils.FcmUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val setPushTokenSavedStateUseCase: SetPushTokenSavedStateUseCase,
    private val insertPushTokenUseCase: InsertPushTokenUseCase,
) : BaseViewModel() {

    private val _localOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
    val localOnboardingState: StateFlow<Int> = _localOnboardingState.asStateFlow()

//    private val _remoteOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
//    val remoteOnboardingState: StateFlow<Int> = _remoteOnboardingState.asStateFlow()

    private val _remoteOnboardingState = MutableStateFlow(false)
    val remoteOnboardingState: StateFlow<Boolean> = _remoteOnboardingState.asStateFlow()

    fun signInWithKeyme(
        token: String,
    ) {
        apiCall(apiRequest = { signInUseCase.invoke(token) }) {
            Timber.d("$it")
            _remoteOnboardingState.emit(
                true,
//                when {
//                    it.nickname.isNullOrBlank() -> OnboardingStepsEnum.NICKNAME
//                    it.onboardingTestResultId == null -> OnboardingStepsEnum.GUIDE_01
//                    else -> OnboardingStepsEnum.MY_DAILY
//                }.ordinal,
            )
            FcmUtil.getToken()?.let { token ->
                insertPushTokenUseCase.invoke(token)
                    .onSuccess { setPushTokenSavedStateUseCase.invoke(true) }
                    .onFailure { setPushTokenSavedStateUseCase.invoke(false) }
            }
        }
    }
}
