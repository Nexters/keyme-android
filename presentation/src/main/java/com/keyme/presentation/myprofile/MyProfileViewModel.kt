package com.keyme.presentation.myprofile

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetMyStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val getMyStatisticsUseCase: GetMyStatisticsUseCase,
) : BaseViewModel() {
    private val _mySimilarStatisticsState = MutableStateFlow(MemberStatistics())
    val mySimilarStatisticsState = _mySimilarStatisticsState.asStateFlow()

    private val _myDifferentStatisticsState = MutableStateFlow(MemberStatistics())
    val myDifferentStatisticsState = _myDifferentStatisticsState.asStateFlow()

    private val _myProfileUiState = MutableStateFlow(MyProfileUiState())
    val myProfileUiState = _myProfileUiState.asStateFlow()

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    init {
        showToolTip()
        loadMyStatistics()
    }

    private fun loadMyStatistics() {
        apiCall(apiRequest = { getMyStatisticsUseCase.invoke(type = MemberStatistics.StatisticsType.SIMILAR) }) {
            _mySimilarStatisticsState.value = it
        }
        apiCall(apiRequest = { getMyStatisticsUseCase.invoke(type = MemberStatistics.StatisticsType.DIFFERENT) }) {
            _myDifferentStatisticsState.value = it
        }
    }

    private var toolTipTimerJob: Job? = null

    fun showToolTip() {
        if (_myProfileUiState.value.showToolTip.not()) {
            _myProfileUiState.value = _myProfileUiState.value.copy(showToolTip = true)

            toolTipTimerJob = baseViewModelScope.launch {
                delay(3000L)
                dismissToolTip()
            }
        }
    }

    fun dismissToolTip() {
        _myProfileUiState.value = _myProfileUiState.value.copy(showToolTip = false)

        baseViewModelScope.launch {
            toolTipTimerJob?.cancelAndJoin()
            toolTipTimerJob = null
        }
    }
}

data class MyProfileUiState(val showToolTip: Boolean = false)
