package com.keyme.domain.repository

import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse

interface MemberRepository {

    suspend fun getStatistics(
        memberId: String,
        type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse
}
