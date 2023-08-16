package com.keyme.presentation.onboarding

import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.room.UserAuth
import com.keyme.domain.usecase.GetUserAuthUseCase
import com.keyme.domain.usecase.InsertUserAuthUseCase
import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.BaseViewModel
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
        }
    }
}
