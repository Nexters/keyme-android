package com.keyme.presentation.onboarding

import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.InsertPushTokenUseCase
import com.keyme.domain.usecase.SetPushTokenSavedStateUseCase
import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.room.UserAuth
import com.keyme.domain.usecase.GetUserAuthUseCase
import com.keyme.domain.usecase.InsertUserAuthUseCase
import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.utils.FcmUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    getUserAuthUseCase: GetUserAuthUseCase,
    private val insertUserAuthUseCase: InsertUserAuthUseCase,
    private val signInUseCase: SignInUseCase,
    private val setPushTokenSavedStateUseCase: SetPushTokenSavedStateUseCase,
    private val insertPushTokenUseCase: InsertPushTokenUseCase,
) : BaseViewModel() {

    val userAuthState: StateFlow<UserAuth?> = getUserAuthUseCase.getUserAuth().stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _remoteOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
    val remoteOnboardingState: StateFlow<Int> = _remoteOnboardingState.asStateFlow()

    fun signInWithKeyme(
        token: String,
    ) {
        apiCall(apiRequest = { signInUseCase.invoke(token) }) {
            Timber.d("$it")
            insertUserAuthUseCase.invoke(
                UserAuth(
                    id = it.memberId,
                    nickname = it.nickname,
                    profileImage = it.profileImage,
                    profileThumbnail = it.profileThumbnail,
                    onboardingTestResultId = it.onboardingTestResultId,
                    friendCode = it.friendCode,
                    accessToken = it.token.accessToken,
                ),
            )

            _remoteOnboardingState.emit(
                OnboardingStepsEnum.MY_DAILY.ordinal,
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
