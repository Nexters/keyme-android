package com.keyme.presentation.takekeymetest

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.TestRegisterResponse
import com.keyme.domain.entity.response.TestResult
import com.keyme.domain.entity.response.isRegister
import com.keyme.domain.usecase.GetKeymeTestResultUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.RegistrationResultCodeUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeKeymeTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val registrationResultCodeUseCase: RegistrationResultCodeUseCase,
    private val getKeymeTestResultUseCase: GetKeymeTestResultUseCase,
) : BaseViewModel() {
    private val testId = savedStateHandle[TakeKeymeTestDestination.testIdArg] ?: 0
    val keymeTestUrl = "https://keyme-frontend.vercel.app/test/$testId"

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    private val _keymeTestResultState = MutableStateFlow<TestResult?>(null)
    val keymeTestResultState = _keymeTestResultState.asStateFlow()

    private val _testRegisterResponseState = MutableStateFlow(TestRegisterResponse.EMPTY)

    init {
        _testRegisterResponseState
            .filter { it.isRegister() }
            .distinctUntilChanged()
            .onEach {
                if (registrationResultCodeUseCase(it.resultCode)) {
                    getTestResult(it.testResultId)
                }
            }.launchIn(baseViewModelScope)
    }

    fun updateTestResult(result: TestRegisterResponse) {
        baseViewModelScope.launch {
            _testRegisterResponseState.value = result
        }
    }

    private fun getTestResult(testResultId: Int) {
        apiCall(apiRequest = { getKeymeTestResultUseCase(testResultId) }) {
            _keymeTestResultState.value = it
        }
    }
}
