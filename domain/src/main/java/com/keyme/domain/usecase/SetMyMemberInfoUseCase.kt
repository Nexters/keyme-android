package com.keyme.domain.usecase

import com.keyme.domain.entity.member.Member
import com.keyme.domain.repository.MyMemberInfoRepository
import javax.inject.Inject

class SetMyMemberInfoUseCase @Inject constructor(
    private val myMemberInfoRepository: MyMemberInfoRepository,
) {
    suspend operator fun invoke(
        member: Member,
    ) {
        return myMemberInfoRepository.updateInfo(member)
    }
}
