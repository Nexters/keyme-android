package com.keyme.domain.repository

import com.keyme.domain.entity.response.EmptyResponse
import com.keyme.domain.entity.response.MemberResponse
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse

interface MemberRepository {

    suspend fun getMember(memberId: Int): MemberResponse

    suspend fun getStatistics(
        memberId: String,
        type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse

    suspend fun withdraw(): EmptyResponse
}
