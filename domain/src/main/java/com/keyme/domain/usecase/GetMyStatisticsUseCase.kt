package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.UserRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetMyStatisticsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val memberRepository: MemberRepository,
) {
    suspend operator fun invoke(type: MemberStatistics.StatisticsType) = apiResult {
        val memberId = userRepository.getUser().filterNotNull().first { it.id != 0 }.id
        memberRepository.getStatistics(memberId = memberId.toString(), type = type)
    }
}
