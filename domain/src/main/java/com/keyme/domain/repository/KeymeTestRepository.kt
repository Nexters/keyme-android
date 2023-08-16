package com.keyme.domain.repository

import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse

interface KeymeTestRepository {

    suspend fun getOnBoardingTest(): OnBoardingTestResponse

    suspend fun getDailyTest(): DailyTestResponse
}
