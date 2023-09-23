package com.keyme.presentation.editprofile

import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.onApiError
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.GetMyUserInfoUseCase
import com.keyme.domain.usecase.SetMyMemberInfoUseCase
import com.keyme.domain.usecase.UpdateMemberUseCase
import com.keyme.domain.usecase.UploadProfileImageUseCase
import com.keyme.domain.usecase.VerifyNicknameUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    getMyUserInfoUseCase: GetMyUserInfoUseCase,
    private val verifyNicknameUseCase: VerifyNicknameUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val updateMemberUseCase: UpdateMemberUseCase,
    private val setMyMemberInfoUseCase: SetMyMemberInfoUseCase,
) : BaseViewModel() {

    private val _editProfileUiState = MutableStateFlow(EditProfileUiState())
    val editProfileUiState = _editProfileUiState.asStateFlow()

    private val _editProfileUiEvent = MutableSharedFlow<EditProfileUiEvent>()
    val editProfileUiEvent = _editProfileUiEvent.asSharedFlow()

    private val myMemberInfo: StateFlow<Member> = getMyUserInfoUseCase()
        .filterNotNull()
        .stateIn(viewModelScope, SharingStarted.Eagerly, Member.EMPTY)

    init {
        myMemberInfo.onEach {
            _editProfileUiState.value = _editProfileUiState.value.copy(nickname = it.nickname)
        }.launchIn(baseViewModelScope)
    }

    fun uploadProfileImage(imageString: String) {
        apiCall(apiRequest = { uploadProfileImageUseCase.invoke(imageString = imageString) }) {
            setMyMemberInfoUseCase(
                myMemberInfo.value.copy(
                    profileImage = it.originalUrl,
                    profileThumbnail = it.thumbnailUrl,
                ),
            )
        }
    }

    fun onNicknameChange(nickname: String) {
        baseViewModelScope.launch {
            verifyNicknameUseCase(nickname)
                .onSuccess {
                    _editProfileUiState.value = _editProfileUiState.value.copy(
                        nickname = nickname,
                        isValidNickname = it.valid,
                        verifyDescription = it.description,
                    )
                }.onApiError { code, message ->
                    _editProfileUiState.value = _editProfileUiState.value.copy(isValidNickname = false)

                    baseViewModelScope.launch {
                        uiEventManager.onEvent(UiEvent.Toast(message))
                    }
                }.onFailure {
                    _editProfileUiState.value = _editProfileUiState.value.copy(isValidNickname = false)

                    baseViewModelScope.launch {
                        uiEventManager.onEvent(UiEvent.Toast(it.message ?: ""))
                    }
                }
        }
    }

    fun updateMember() {
        // todo 프로필 사진 선택
//        if (uploadProfileImageState.value == null) return
        apiCall(
            apiRequest = {
                updateMemberUseCase.invoke(
                    nickname = _editProfileUiState.value.nickname,
                    profileImage = myMemberInfo.value.profileImage,
                    profileThumbnail = myMemberInfo.value.profileThumbnail,
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
                    isOnboardingClear = myMemberInfo.value.isOnboardingClear,
                    accessToken = myMemberInfo.value.accessToken,
                )
            )
            _editProfileUiEvent.emit(EditProfileUiEvent.UpdateMemberSuccess)
        }
    }
}

data class EditProfileUiState(
    val nickname: String = "",
    val isValidNickname: Boolean = false,
    val verifyDescription: String = "",
)

sealed interface EditProfileUiEvent {
    object UpdateMemberSuccess: EditProfileUiEvent
}
