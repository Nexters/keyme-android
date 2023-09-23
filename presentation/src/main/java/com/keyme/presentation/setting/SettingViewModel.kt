package com.keyme.presentation.setting

import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.MyMemberInfoRepository
import com.keyme.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val myMemberInfoRepository: MyMemberInfoRepository,
    private val memberRepository: MemberRepository,
) : BaseViewModel() {

    fun logOut() {
        baseViewModelScope.launch {
            myMemberInfoRepository.clearInfo()
        }
    }

    fun withdraw() {
        baseViewModelScope.launch {
            if (memberRepository.withdraw().code == "200") {
                myMemberInfoRepository.clearInfo()
            }
        }
    }
}
