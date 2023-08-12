package com.keyme.domain.repository.keymetest

import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatisticsResponse

interface KeymeTestResultRepository {

    suspend fun getStatistics(id: String): KeymeTestResultStatisticsResponse
}
