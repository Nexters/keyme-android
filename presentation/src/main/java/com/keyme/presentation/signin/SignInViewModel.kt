package com.keyme.presentation.signin

import com.keyme.domain.usecase.SignInUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.signin.enums.SignInStateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : BaseViewModel() {

    private val _keymeSignInState = MutableStateFlow(SignInStateEnum.NICKNAME)
    val keymeSignInState: StateFlow<SignInStateEnum> = _keymeSignInState.asStateFlow()

    fun signInWithKeyme(
        token: String,
    ) {
        apiCall(apiRequest = { signInUseCase.invoke(token) }) {
            _keymeSignInState.value = when {
                it.nickname.isNullOrBlank() -> SignInStateEnum.NICKNAME
                it.onboardingTestResultId == null -> SignInStateEnum.KEYME_TEST
                else -> SignInStateEnum.MY_DAILY
            }
        }
    }
}
