package com.keyme.presentation.myprofile

import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.usecase.GetMyStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getMyStatisticsUseCase: GetMyStatisticsUseCase,
) : BaseViewModel() {
    private val _myStatisticsState = MutableStateFlow(MemberStatistics())
    val myStatisticsState = _myStatisticsState.asStateFlow()

    init {
        loadMyStatistics()
    }

    private fun loadMyStatistics() {
        apiCall(apiRequest = { getMyStatisticsUseCase.invoke(type = MemberStatistics.StatisticsType.SIMILAR) }) {
            baseViewModelScope.launch {
                _myStatisticsState.value = it
            }
        }
    }
}
