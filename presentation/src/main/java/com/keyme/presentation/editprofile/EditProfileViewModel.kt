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

    private val myMemberInfo: StateFlow<Member> = getMyUserInfoUseCase()
        .filterNotNull()
        .stateIn(viewModelScope, SharingStarted.Eagerly, Member.EMPTY)

    private val oldMemberInfo = myMemberInfo.value

    private val _editProfileUiState = MutableStateFlow(
        EditProfileUiState(
            nickname = oldMemberInfo.nickname,
            profileImageUrl = oldMemberInfo.profileImage,
            isValidNickname = verifyNickname(oldMemberInfo.nickname),
        ),
    )
    val editProfileUiState = _editProfileUiState.asStateFlow()

    private val _editProfileUiEvent = MutableSharedFlow<EditProfileUiEvent>()
    val editProfileUiEvent = _editProfileUiEvent.asSharedFlow()

    init {
        _editProfileUiState.onEach {
            val updateAvailable = checkUpdateAvailable(oldMemberInfo, it)
            _editProfileUiState.value = _editProfileUiState.value.copy(updateAvailable = updateAvailable)
        }.launchIn(baseViewModelScope)
    }

    private fun checkUpdateAvailable(oldValue: Member, newValue: EditProfileUiState): Boolean {
        return if (newValue.isValidNickname) {
            oldValue.nickname != newValue.nickname ||
                oldValue.profileImage != newValue.profileImageUrl ||
                oldValue.profileThumbnail != newValue.profileThumbnailUrl
        } else {
            false
        }
    }

    fun uploadProfileImage(imageString: String) {
        apiCall(apiRequest = { uploadProfileImageUseCase.invoke(imageString = imageString) }) {
            _editProfileUiState.value = _editProfileUiState.value.copy(
                profileImageUrl = it.originalUrl,
                profileThumbnailUrl = it.thumbnailUrl,
            )
        }
    }

    fun onNicknameChange(nickname: String) {
        if (verifyNickname(nickname)) {
            baseViewModelScope.launch {
                verifyNicknameUseCase(nickname)
                    .onSuccess {
                        _editProfileUiState.value = _editProfileUiState.value.copy(
                            nickname = nickname,
                            isValidNickname = it.valid && verifyNickname(nickname),
                            verifyDescription = "사용 가능합니다",
                        )
                    }.onApiError { code, _ ->
                        val description = if (code == "201") "이미 사용 중인 닉네임입니다" else ""
                        _editProfileUiState.value = _editProfileUiState.value.copy(isValidNickname = false, verifyDescription = description)
                    }.onFailure {
                        _editProfileUiState.value = _editProfileUiState.value.copy(isValidNickname = false)

                        baseViewModelScope.launch {
                            uiEventManager.onEvent(UiEvent.Toast(it.message ?: ""))
                        }
                    }
            }
        } else {
            _editProfileUiState.value = _editProfileUiState.value.copy(isValidNickname = false, verifyDescription = "")
        }
    }

    private fun verifyNickname(nickname: String): Boolean {
        return nickname.length in 2..6
    }

    fun updateMember() {
        apiCall(
            apiRequest = {
                val updateMemberInfo = _editProfileUiState.value
                updateMemberUseCase.invoke(
                    nickname = updateMemberInfo.nickname,
                    profileImage = updateMemberInfo.profileImageUrl,
                    profileThumbnail = updateMemberInfo.profileThumbnailUrl,
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
                ),
            )
            _editProfileUiEvent.emit(EditProfileUiEvent.UpdateMemberSuccess)
        }
    }
}

data class EditProfileUiState(
    val nickname: String = "",
    val profileImageUrl: String = "",
    val profileThumbnailUrl: String = "",
    val isValidNickname: Boolean = false,
    val verifyDescription: String = "",
    val updateAvailable: Boolean = false,
)

sealed interface EditProfileUiEvent {
    object UpdateMemberSuccess : EditProfileUiEvent
}
