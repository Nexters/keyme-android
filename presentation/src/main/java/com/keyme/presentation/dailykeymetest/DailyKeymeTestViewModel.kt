package com.keyme.presentation.dailykeymetest

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Test
import com.keyme.domain.entity.response.TestStatistic
import com.keyme.domain.usecase.GetKeymeTestStatisticUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetOnboardingKeymeTestUseCase
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
import javax.inject.Inject

@HiltViewModel
class DailyKeymeTestViewModel @Inject constructor(
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val getKeymeTestStatisticUseCase: GetKeymeTestStatisticUseCase,
    private val getOnboardingKeymeTestUseCase: GetOnboardingKeymeTestUseCase,
) : BaseViewModel() {

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    private val _keymeTestState = MutableStateFlow(Test.EMPTY)
    val keymeTestState = _keymeTestState.asStateFlow()

    private val _keymeTestStatisticState = MutableStateFlow<TestStatistic?>(null)
    val keymeTestStatisticState = _keymeTestStatisticState.asStateFlow()

    init {
        load()
    }

    private fun load() {
        // NOTE: 데일리 문제를 제공하는 대신 온보딩 문제를 계속 공유할 수 있게 하는 플로우로 수정
        apiCall(apiRequest = { getOnboardingKeymeTestUseCase() }) {
            _keymeTestState.value = it
        }

        _keymeTestState
            .filter { it != Test.EMPTY }
            .distinctUntilChanged()
            .onEach {
                apiCall(apiRequest = { getKeymeTestStatisticUseCase(it.testId) }) { statistic ->
                    _keymeTestStatisticState.value = statistic
                }
            }.launchIn(baseViewModelScope)
    }
}
