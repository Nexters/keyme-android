package com.keyme.presentation.takekeymetest

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.response.TestRegisterResponse
import com.keyme.domain.entity.response.TestResult
import com.keyme.domain.entity.response.isRegister
import com.keyme.domain.usecase.GetKeymeTestResultUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.RegistrationResultCodeUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeKeymeTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val myCharacterUseCase: GetMyCharacterUseCase,
    private val registrationResultCodeUseCase: RegistrationResultCodeUseCase,
    private val getKeymeTestResultUseCase: GetKeymeTestResultUseCase,
) : BaseViewModel() {
    private val testId = savedStateHandle[TakeKeymeTestDestination.testIdArg] ?: 0
    val keymeTestUrl =
        "https://keyme-frontend.vercel.app/test/$testId?nickname=${myCharacterUseCase().nickname}"

    private val _keymeTestResultState = MutableStateFlow<TestResult?>(null)
    val keymeTestResultState = _keymeTestResultState.asStateFlow()

    private val _testRegisterResponseState = MutableStateFlow(TestRegisterResponse.EMPTY)

    init {
        _testRegisterResponseState
            .filter { it.isRegister() }
            .distinctUntilChanged()
            .onEach {
                registrationResult(it.resultCode)
            }.launchIn(baseViewModelScope)
    }

    fun updateTestResult(result: TestRegisterResponse) {
        baseViewModelScope.launch {
            _testRegisterResponseState.value = result
        }
    }

    private fun registrationResult(resultCode: String) {
        apiCall(apiRequest = { registrationResultCodeUseCase(resultCode) }) {
            getTestResult(_testRegisterResponseState.value.testResultId)
        }
    }

    private fun getTestResult(testResultId: Int) {
        apiCall(apiRequest = { getKeymeTestResultUseCase(testResultId) }) {
            _keymeTestResultState.value = it
        }
    }
}
