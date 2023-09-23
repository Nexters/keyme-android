package com.keyme.presentation.onboarding

import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.Test
import com.keyme.domain.entity.response.UploadProfileImage
import com.keyme.domain.entity.response.VerifyNickname
import com.keyme.domain.usecase.GetMyUserInfoUseCase
import com.keyme.domain.usecase.GetOnboardingKeymeTestUseCase
import com.keyme.domain.usecase.InsertPushTokenUseCase
import com.keyme.domain.usecase.SetMyMemberInfoUseCase
import com.keyme.domain.usecase.SetPushTokenSavedStateUseCase
import com.keyme.domain.usecase.SignInUseCase
import com.keyme.domain.usecase.SyncMyMemberInfoUseCase
import com.keyme.domain.usecase.UpdateMemberUseCase
import com.keyme.domain.usecase.UploadProfileImageUseCase
import com.keyme.domain.usecase.VerifyNicknameUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.utils.FcmUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    getMyUserInfoUseCase: GetMyUserInfoUseCase,
    private val signInUseCase: SignInUseCase,
    private val setMyMemberInfoUseCase: SetMyMemberInfoUseCase,
    private val insertPushTokenUseCase: InsertPushTokenUseCase,
    private val setPushTokenSavedStateUseCase: SetPushTokenSavedStateUseCase,
    private val verifyNicknameUseCase: VerifyNicknameUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val updateMemberUseCase: UpdateMemberUseCase,
    private val getOnboardingKeymeTestUseCase: GetOnboardingKeymeTestUseCase,
    private val syncMyMemberInfoUseCase: SyncMyMemberInfoUseCase,
) : BaseViewModel() {

    private val myMemberInfo: StateFlow<Member?> =
        getMyUserInfoUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null)

    private val _onBoardingPageUiState =
        MutableStateFlow(OnBoardingPageUiState(OnboardingStepsEnum.KAKAO_SIGN_IN))
    val onBoardingPageUiState = _onBoardingPageUiState.asStateFlow()

    private val _remoteOnboardingState = MutableStateFlow(OnboardingStepsEnum.KAKAO_SIGN_IN.ordinal)
    val remoteOnboardingState: StateFlow<Int> = _remoteOnboardingState.asStateFlow()

    private var verifyNicknameJob: Job? = null
    private val _verifyNicknameState = MutableStateFlow<VerifyNickname?>(null)
    val verifyNicknameState: StateFlow<VerifyNickname?> = _verifyNicknameState.asStateFlow()

    private val _uploadProfileImageState = MutableStateFlow<UploadProfileImage?>(null)
    val uploadProfileImageState: StateFlow<UploadProfileImage?> = _uploadProfileImageState.asStateFlow()

    private val _onboardingKeymeTestState = MutableStateFlow<Test?>(null)
    val onboardingKeymeTestState: StateFlow<Test?> = _onboardingKeymeTestState.asStateFlow()

    init {
        syncMyMemberInfo()

        myMemberInfo.map {
            when {
                it?.accessToken == null -> OnboardingStepsEnum.KAKAO_SIGN_IN
                it.nickname.isNullOrBlank() -> OnboardingStepsEnum.NICKNAME
                it.isOnboardingClear.not() -> {
                    getOnboardingKeymeTest()
                    OnboardingStepsEnum.GUIDE_01
                }

                else -> OnboardingStepsEnum.MY_DAILY
            }
        }.filterNotNull().onEach {
            _onBoardingPageUiState.value = OnBoardingPageUiState(it)
        }.launchIn(baseViewModelScope)
    }

    private fun syncMyMemberInfo() {
        baseViewModelScope.launch {
            syncMyMemberInfoUseCase()
        }
    }

    fun signInWithKeyme(
        token: String,
    ) {
        apiCall(apiRequest = { signInUseCase.invoke(token) }) {
            Timber.d("$it")
            setMyMemberInfoUseCase.invoke(
                Member(
                    id = it.id,
                    nickname = it.nickname ?: "",
                    profileImage = it.profileImage,
                    profileThumbnail = it.profileThumbnail,
                    isOnboardingClear = it.isOnboardingClear,
                    friendCode = it.friendCode ?: "",
                    accessToken = it.token.accessToken,
                ),
            )
        }
    }

    fun verifyNickname(
        nickname: String,
    ) {
        verifyNicknameJob?.cancel()
        verifyNicknameJob = viewModelScope.launch {
            apiCall(apiRequest = { verifyNicknameUseCase.invoke(nickname) }) {
                _verifyNicknameState.emit(it)
            }
        }
    }

    fun uploadProfileImage(
        imageString: String,
    ) {
        apiCall(
            apiRequest = {
                uploadProfileImageUseCase.invoke(imageString = imageString)
            },
        ) {
            _uploadProfileImageState.emit(it)
        }
    }

    fun updateMember(
        nickname: String,
        originalUrl: String,
        thumbnailUrl: String,
    ) {
        // todo 프로필 사진 선택
//        if (uploadProfileImageState.value == null) return
        apiCall(
            apiRequest = {
                updateMemberUseCase.invoke(
                    nickname = nickname,
                    profileImage = originalUrl,
                    profileThumbnail = thumbnailUrl,
                )
            },
        ) {
            setMyMemberInfoUseCase.invoke(
                Member(
                    id = it.id,
                    nickname = it.nickname,
                    profileImage = it.profileImage,
                    profileThumbnail = it.profileThumbnail,
                    friendCode = it.friendCode ?: "",
                    isOnboardingClear = myMemberInfo.value?.isOnboardingClear ?: false,
                    accessToken = myMemberInfo.value?.accessToken ?: "",
                ),
            )
            FcmUtil.getToken()?.let { token ->
                insertPushTokenUseCase.invoke(token).onSuccess {
                    setPushTokenSavedStateUseCase.invoke(true)
                }.onFailure {
                    setPushTokenSavedStateUseCase.invoke(false)
                }
            }
        }
    }

    private fun getOnboardingKeymeTest() {
        apiCall(apiRequest = { getOnboardingKeymeTestUseCase.invoke() }) {
            _onboardingKeymeTestState.emit(it)
        }
    }

    fun onPageIndexChange(index: Int) {
        _onBoardingPageUiState.value = OnBoardingPageUiState(OnboardingStepsEnum.values()[index])
    }
}

data class OnBoardingPageUiState(val currentPage: OnboardingStepsEnum) {
    val pageSize: Int = OnboardingStepsEnum.values().size
}
