package com.keyme.presentation.myprofile

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.onApiError
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.usecase.GetDailyKeymeTestUseCase
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetMyStatisticsUseCase
import com.keyme.domain.usecase.GetOnboardingKeymeTestUseCase
import com.keyme.presentation.BaseViewModel
import com.keyme.presentation.UiEvent
import com.keyme.presentation.utils.KeymeLinkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getMyCharacterUseCase: GetMyCharacterUseCase,
    private val getMyStatisticsUseCase: GetMyStatisticsUseCase,
    private val getOnboardingKeymeTestUseCase: GetOnboardingKeymeTestUseCase,
) : BaseViewModel() {

    private val _mySimilarStatisticsState = MutableStateFlow(MemberStatistics())
    private val _myDifferentStatisticsState = MutableStateFlow(MemberStatistics())

    val myProfileUiState = combine(
        getMyCharacterUseCase(),
        _mySimilarStatisticsState,
        _myDifferentStatisticsState,
    ) { myCharacter, similarStatistics, differentStatistics ->
        if (statisticsEmpty(similarStatistics, differentStatistics)) {
            MyProfileUiState.EmptyStatistics(
                myCharacter = myCharacter,
                testLink = KeymeLinkUtil.getTestLink(getTestForShare().testId),
            )
        } else {
            MyProfileUiState.Statistics(
                myCharacter = myCharacter,
                similar = _mySimilarStatisticsState.value,
                different = _myDifferentStatisticsState.value,
            )
        }
    }.stateIn(
        started = SharingStarted.Eagerly,
        initialValue = MyProfileUiState.EmptyStatistics(),
        scope = baseViewModelScope,
    )


    init {
        loadMyStatistics()
    }

    private suspend fun getTestForShare() = suspendCancellableCoroutine { continuation ->
        apiCall(apiRequest = { getOnboardingKeymeTestUseCase() }) {
            continuation.resume(it)
        }
    }

    private fun statisticsEmpty(
        similarStatistics: MemberStatistics,
        differentStatistics: MemberStatistics,
    ): Boolean {
        return similarStatistics.results.isEmpty() || differentStatistics.results.isEmpty()
    }

    private fun loadMyStatistics() {
        baseViewModelScope.launch {
            getMyStatisticsUseCase(type = MemberStatistics.StatisticsType.SIMILAR)
                .onSuccess {
                    _mySimilarStatisticsState.value = it
                }.onApiError { code, message ->
                    _mySimilarStatisticsState.value = MemberStatistics()
                }.onFailure {
                    baseViewModelScope.launch {
                        uiEventManager.onEvent(UiEvent.Toast(it.message ?: ""))
                    }
                }

            getMyStatisticsUseCase(type = MemberStatistics.StatisticsType.DIFFERENT)
                .onSuccess {
                    _myDifferentStatisticsState.value = it
                }.onApiError { code, message ->
                    _myDifferentStatisticsState.value = MemberStatistics()
                }.onFailure {
                    baseViewModelScope.launch {
                        uiEventManager.onEvent(UiEvent.Toast(it.message ?: ""))
                    }
                }
        }
    }
}

sealed class MyProfileUiState(open val myCharacter: Member) {
    data class EmptyStatistics(
        override val myCharacter: Member = Member.EMPTY,
        val testLink: String = "",
    ) : MyProfileUiState(myCharacter)

    data class Statistics(
        override val myCharacter: Member = Member.EMPTY,
        val similar: MemberStatistics,
        val different: MemberStatistics,
    ) : MyProfileUiState(myCharacter)
}
