package com.keyme.domain.repository

import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse
import com.keyme.domain.entity.response.TestStatisticResponse

interface KeymeTestRepository {

    suspend fun getOnBoardingTest(): OnBoardingTestResponse

    suspend fun getDailyTest(): DailyTestResponse

    suspend fun getTestStatistic(testId: Int): TestStatisticResponse
}
