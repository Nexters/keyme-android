package com.keyme.presentation.dailykeymetest

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.Test
import com.keyme.domain.usecase.GetDailyKeymeTestUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DailyKeymeTestViewModel @Inject constructor(
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val getDailyKeymeTestUseCase: GetDailyKeymeTestUseCase,
): BaseViewModel() {

    private val _myCharacterState = MutableStateFlow(Member.EMPTY)
    val myCharacterState = _myCharacterState.asStateFlow()

    private val _dailyKeymeTestState = MutableStateFlow(Test.EMPTY)
    val dailyKeymeTestState = _dailyKeymeTestState.asStateFlow()

//    private val _dailyTestStatisticsState = MutableStateFlow(TestSta)

    init {
        load()
    }

    private fun load() {
        apiCall(apiRequest = { getDailyKeymeTestUseCase() }) {
            _dailyKeymeTestState.value = it
        }
        _myCharacterState.value = getMyCharacterUseCase.invoke()
    }
}
