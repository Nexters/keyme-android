package com.keyme.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.Sample
import com.keyme.domain.usecase.GetSampleUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSampleUseCase: GetSampleUseCase,
) : BaseViewModel() {

    private val _getSampleUiState = MutableStateFlow<UiState<Sample>>(UiState.Loading)
    val getSampleUiState: StateFlow<UiState<Sample>> get() = _getSampleUiState

    fun getSample() {
        baseViewModelScope.launch {
            getSampleUseCase.invoke()
                .onSuccess { sample ->
                    _getSampleUiState.value = UiState.Success(sample)
                }
                .onFailure { throwable ->
                    _getSampleUiState.value = UiState.Failure(throwable)
                }
        }
    }
}
