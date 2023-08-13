package com.keyme.domain.usecase

import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.repository.MemberRepository
import javax.inject.Inject

class GetMyStatisticsUseCase @Inject constructor(
    private val memberRepository: MemberRepository,
) {
    suspend operator fun invoke(type: MemberStatistics.StatisticsType) =
        memberRepository.getStatistics(memberId = "1", type = type)
}
