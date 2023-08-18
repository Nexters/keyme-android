package com.keyme.presentation.myprofile

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.QuestionStatistic
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetMySolvedScoreUseCase
import com.keyme.domain.usecase.GetQuestionSolvedScoreListUseCase
import com.keyme.domain.usecase.GetQuestionStatisticsUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.myprofile.ui.KeymeQuestionResultDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class KeymeQuestionResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getQuestionStatisticsUseCase: GetQuestionStatisticsUseCase,
    private val getMySolvedScoreUseCase: GetMySolvedScoreUseCase,
    private val getQuestionSolvedScoreListUseCase: GetQuestionSolvedScoreListUseCase,
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
) : BaseViewModel() {
    private val questionId: String? = savedStateHandle[KeymeQuestionResultDestination.questionIdArg]

    private val _statisticsState = MutableStateFlow(QuestionStatistic.EMPTY)
    val statisticsState = _statisticsState.asStateFlow()

    val myCharacterState = getMyCharacterUseCase().stateIn(
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Member.EMPTY,
        scope = baseViewModelScope,
    )

    private val _myScoreState = MutableStateFlow<QuestionSolvedScore?>(null)
    val myScoreState = _myScoreState.asStateFlow()

    val solvedScorePagingFlow = questionId?.let {
        myCharacterState.filter { it != Member.EMPTY }.flatMapLatest {
            getQuestionSolvedScoreListUseCase(questionId, it.id).flow.cachedIn(baseViewModelScope)
        }
    } ?: flowOf(PagingData.empty())

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
