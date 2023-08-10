package com.keyme.presentation.myprofile

import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.Circle
import com.keyme.domain.usecase.GetResultCircleUseCase
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val resultCircleUseCase: GetResultCircleUseCase,
) : BaseViewModel() {
    private val _resultCircleState = MutableStateFlow<List<Circle>>(listOf())
    val resultCircleState = _resultCircleState.asStateFlow()

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
