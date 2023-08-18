package com.keyme.presentation.myprofile

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetMyStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
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

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    init {
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
}
