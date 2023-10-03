package com.keyme.app.ui

import com.keyme.domain.repository.MyMemberInfoRepository
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class KeymeAppStateViewModel @Inject constructor(
    private val myMemberInfoRepository: MyMemberInfoRepository,
) : BaseViewModel() {

    val myMemberInfoState = myMemberInfoRepository
        .getInfo()
        .stateIn(
            started = SharingStarted.Eagerly,
            scope = baseViewModelScope,
            initialValue = null,
        )
}
