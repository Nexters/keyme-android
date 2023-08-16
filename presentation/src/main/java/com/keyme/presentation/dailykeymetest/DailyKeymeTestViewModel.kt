package com.keyme.presentation.dailykeymetest

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Test
import com.keyme.domain.entity.response.TestStatistic
import com.keyme.domain.usecase.GetDailyKeymeTestUseCase
import com.keyme.domain.usecase.GetKeymeTestStatisticUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DailyKeymeTestViewModel @Inject constructor(
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val getDailyKeymeTestUseCase: GetDailyKeymeTestUseCase,
    private val getKeymeTestStatisticUseCase: GetKeymeTestStatisticUseCase,
) : BaseViewModel() {

    private val _myCharacterState = MutableStateFlow(Member.EMPTY)
    val myCharacterState = _myCharacterState.asStateFlow()

    private val _dailyKeymeTestState = MutableStateFlow(Test.EMPTY)
    val dailyKeymeTestState = _dailyKeymeTestState.asStateFlow()

    private val _dailyKeymeTestStatisticState = MutableStateFlow<TestStatistic?>(null)
    val dailyKeymeTestStatisticState = _dailyKeymeTestStatisticState.asStateFlow()

    init {
        load()
    }

    private fun load() {
        apiCall(apiRequest = { getDailyKeymeTestUseCase() }) {
            _dailyKeymeTestState.value = it
        }
        _myCharacterState.value = getMyCharacterUseCase.invoke()

        dailyKeymeTestState
            .map { it.testId }
            .filter { it != 0 }
            .distinctUntilChanged()
            .onEach {
                apiCall(apiRequest = { getKeymeTestStatisticUseCase(it) }) {statistic ->
                    _dailyKeymeTestStatisticState.value = statistic
                }
            }.launchIn(baseViewModelScope)
    }
}
