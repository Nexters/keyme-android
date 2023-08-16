package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse
import com.keyme.domain.repository.KeymeTestRepository
import javax.inject.Inject

class KeymeTestRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : KeymeTestRepository {
    override suspend fun getOnBoardingTest(): OnBoardingTestResponse {
        return keymeApi.getOnBoardingTest()
    }

    override suspend fun getDailyTest(): DailyTestResponse {
        return keymeApi.getDailyTest()
    }
}
