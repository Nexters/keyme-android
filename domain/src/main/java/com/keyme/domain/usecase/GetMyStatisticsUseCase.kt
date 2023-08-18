package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetMyStatisticsUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val memberRepository: MemberRepository,
) {
    suspend operator fun invoke(type: MemberStatistics.StatisticsType) = apiResult {
        val memberId = userAuthRepository.getUserAuth().filterNotNull().first { it.id != 0 }.id
        memberRepository.getStatistics(memberId = memberId.toString(), type = type)
    }
}
