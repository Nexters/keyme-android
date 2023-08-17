package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.request.RegistrationRequest
import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.EmptyResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse
import com.keyme.domain.entity.response.TestResultResponse
import com.keyme.domain.entity.response.TestStatisticResponse
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

    override suspend fun getTestStatistic(testId: Int): TestStatisticResponse {
        return keymeApi.getTestStatistic(testId)
    }

    override suspend fun registrationTestResult(resultCode: String): EmptyResponse {
        return keymeApi.registrationTestResult(RegistrationRequest(resultCode))
    }

    override suspend fun getTestResult(testResultId: String): TestResultResponse {
        return keymeApi.getTestResult(resultId = testResultId)
    }
}
