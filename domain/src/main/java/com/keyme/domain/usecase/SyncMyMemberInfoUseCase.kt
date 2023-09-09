package com.keyme.domain.usecase

import com.keyme.domain.entity.response.toMember
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.MyMemberInfoRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncMyMemberInfoUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
    private val myMemberInfoRepository: MyMemberInfoRepository,
) {
    suspend operator fun invoke() {
        myMemberInfoRepository.getInfo().firstOrNull()?.let { oldInfo ->
            val newInfo = memberRepository.getMember(oldInfo.id).data
            myMemberInfoRepository.updateInfo(newInfo.toMember(oldInfo.accessToken))
        }
    }
}
