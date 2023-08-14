package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse
import com.keyme.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : MemberRepository {
    override suspend fun getStatistics(
        memberId: String,
        type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse {
        return keymeApi.getMemberStatistics(memberId = memberId, type = type)
    }
}
