package com.keyme.presentation.takekeymetest

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.response.TestResult
import com.keyme.domain.usecase.GetKeymeTestResultUseCase
import com.keyme.domain.usecase.RegistrationResultCodeUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TakeKeymeTestViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val registrationResultCodeUseCase: RegistrationResultCodeUseCase,
    private val getKeymeTestResultUseCase: GetKeymeTestResultUseCase,
) : BaseViewModel() {
    private val testId = savedStateHandle[TakeKeymeTestDestination.testIdArg] ?: 0
    val keymeTestUrl = "https://keyme-frontend.vercel.app/test/$testId"

    private val _keymeTestResultState = MutableStateFlow<TestResult?>(null)
    val keymeTestResultState = _keymeTestResultState.asStateFlow()

    fun registrationResult(resultCode: String) {
        apiCall(apiRequest = { registrationResultCodeUseCase(resultCode) }) {
            getTestResult("")
        }
    }

    private fun getTestResult(testResultId: String) {
        apiCall(apiRequest = { getKeymeTestResultUseCase(testResultId) }) {
            _keymeTestResultState.value = it
        }
    }
}
