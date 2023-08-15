package com.keyme.presentation.myprofile

import androidx.lifecycle.SavedStateHandle
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.usecase.GetMySolvedScoreUseCase
import com.keyme.domain.usecase.GetQuestionStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class KeymeQuestionResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuestionStatisticsUseCase: GetQuestionStatisticsUseCase,
    private val getMySolvedScoreUseCase: GetMySolvedScoreUseCase,
) : BaseViewModel() {
    private val questionId: String? = savedStateHandle[KeymeQuestionResultDestination.Argument.questionIdName]

    private val _statisticsState = MutableStateFlow(QuestionStatistic.EMPTY)
    val statisticsState = _statisticsState.asStateFlow()

    private val _myScoreState = MutableStateFlow<QuestionSolvedScore?>(null)
    val myScoreState = _myScoreState.asStateFlow()

    init {
        Timber.d("questionId: $questionId")
        load()
    }

    private fun load() {
        questionId?.let {
            apiCall(apiRequest = { getQuestionStatisticsUseCase.invoke(questionId) }) {
                _statisticsState.value = it
            }
            apiCall(apiRequest = { getMySolvedScoreUseCase.invoke(questionId) }) {
                _myScoreState.value = it
            }
        }
    }

}
