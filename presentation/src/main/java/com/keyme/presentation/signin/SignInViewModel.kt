package com.keyme.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.onApiError
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.UiState
import com.keyme.presentation.signin.enums.SignInStateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    private val _keymeSignInState = MutableStateFlow<UiState<SignInStateEnum>>(UiState.Loading)
    val keymeSignInState: StateFlow<UiState<SignInStateEnum>> = _keymeSignInState

    fun signInWithKeyme(
        token: String,
    ) {
        viewModelScope.launch {
            signInUseCase.invoke(token)
                .onSuccess {
                    _keymeSignInState.value = UiState.Success(
                        when {
                            it.nickname.isNullOrBlank() -> SignInStateEnum.NICKNAME
                            it.onboardingTestResultId == null -> SignInStateEnum.KEYME_TEST
                            else -> SignInStateEnum.MY_DAILY
                        },
                    )
                }
                .onApiError { code, message ->
                    _keymeSignInState.value = UiState.ApiError(code, message)
                }
                .onFailure {
                    _keymeSignInState.value = UiState.Failure(it)
                }
        }
    }
}
