package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.EmptyResponse
import com.keyme.domain.entity.response.MemberResponse
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse
import com.keyme.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : MemberRepository {
    override suspend fun getMember(memberId: Int): MemberResponse {
        return keymeApi.getMember(memberId.toString())
    }

    override suspend fun getStatistics(
        memberId: String,
        type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse {
        return keymeApi.getMemberStatistics(memberId = memberId, type = type)
    }

    override suspend fun withdraw(): EmptyResponse {
        return keymeApi.withdraw()
    }
}
