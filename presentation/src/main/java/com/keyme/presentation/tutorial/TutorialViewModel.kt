package com.keyme.presentation.tutorial

import com.keyme.domain.repository.TutorialRepository
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val tutorialRepository: TutorialRepository,
) : BaseViewModel() {
    val showTutorial = tutorialRepository.isLearned()
        .map { it.not() }
        .stateIn(
            scope = baseViewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false,
        )

    fun setLearned(value: Boolean) {
        tutorialRepository.setLearned(value)
    }
}
