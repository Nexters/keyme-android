package com.keyme.domain.repository

import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.EmptyResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse
import com.keyme.domain.entity.response.TestResultResponse
import com.keyme.domain.entity.response.TestStatisticResponse

interface KeymeTestRepository {

    suspend fun getOnBoardingTest(): OnBoardingTestResponse

    suspend fun getDailyTest(): DailyTestResponse

    suspend fun getTestStatistic(testId: Int): TestStatisticResponse

    suspend fun registrationTestResult(resultCode: String): EmptyResponse

    suspend fun getTestResult(testResultId: String): TestResultResponse
}
