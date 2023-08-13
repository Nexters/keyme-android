package com.keyme.presentation.myprofile

import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.Circle
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.usecase.GetMyStatisticsUseCase
import com.keyme.domain.usecase.GetResultCircleUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val resultCircleUseCase: GetResultCircleUseCase,
    private val getMyStatisticsUseCase: GetMyStatisticsUseCase,
) : BaseViewModel() {
    private val _resultCircleState = MutableStateFlow<List<Circle>>(listOf())
    val resultCircleState = _resultCircleState.asStateFlow()

    val myStatisticsState = flow {
        apiResult { getMyStatisticsUseCase(type = MemberStatistics.StatisticsType.SIMILAR) }
    }

    init {
        getResultCircle()
    }

    private fun getResultCircle() {
        baseViewModelScope.launch {
            resultCircleUseCase().onSuccess {
                _resultCircleState.value = it
            }.onFailure {
            }
        }
    }
}
