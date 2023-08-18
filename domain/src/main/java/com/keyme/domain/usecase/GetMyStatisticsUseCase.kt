package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetMyStatisticsUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val memberRepository: MemberRepository,
) {
    suspend operator fun invoke(type: MemberStatistics.StatisticsType) = apiResult {
        val memberId = userAuthRepository.getUserAuth().firstOrNull()?.id ?: 0
        memberRepository.getStatistics(memberId = memberId.toString(), type = type)
    }
}
