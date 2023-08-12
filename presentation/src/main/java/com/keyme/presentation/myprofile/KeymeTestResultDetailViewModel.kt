package com.keyme.presentation.myprofile

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatistics
import com.keyme.domain.usecase.keymetest.GetKeymeTestResultStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.myprofile.ui.KeymeTestResultDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeymeTestResultDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getKeymeTestResultStatistics: GetKeymeTestResultStatisticsUseCase,
) : BaseViewModel() {
    private val questionId: String? = savedStateHandle[KeymeTestResultDetailDestination.Argument.questionIdName]

    private val _statisticsState = MutableStateFlow<KeymeTestResultStatistics?>(null)
    val statisticsState = _statisticsState.asStateFlow()

    init {
        baseViewModelScope.launch {
            questionId?.let {
                getKeymeTestResultStatistics(questionId).onSuccess {
                    _statisticsState.value = it
                }
            }
        }
    }
}
