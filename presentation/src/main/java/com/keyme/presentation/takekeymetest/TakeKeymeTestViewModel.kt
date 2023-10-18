package com.keyme.presentation.takekeymetest

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.TestRegisterResponse
import com.keyme.domain.entity.response.TestResult
import com.keyme.domain.usecase.GetKeymeTestResultUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.RegistrationResultCodeUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.utils.KeymeLinkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TakeKeymeTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val registrationResultCodeUseCase: RegistrationResultCodeUseCase,
    private val getKeymeTestResultUseCase: GetKeymeTestResultUseCase,
) : BaseViewModel() {
    private val testId = savedStateHandle[TakeKeymeTestDestination.testIdArg] ?: 0
    val keymeTestUrl = KeymeLinkUtil.getTestLink(testId)

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    private val _keymeTestResultState = MutableStateFlow<TestResult?>(null)
    val keymeTestResultState = _keymeTestResultState.asStateFlow()

    fun updateTestResult(result: TestRegisterResponse) {
        getTestResult(result.testResultId)
    }

    private fun getTestResult(testResultId: Int) {
        apiCall(apiRequest = { getKeymeTestResultUseCase(testResultId) }) {
            _keymeTestResultState.value = it
        }
    }
}
