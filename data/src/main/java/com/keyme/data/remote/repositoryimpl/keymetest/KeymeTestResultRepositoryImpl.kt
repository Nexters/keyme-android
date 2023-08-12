package com.keyme.data.remote.repositoryimpl.keymetest

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatisticsResponse
import com.keyme.domain.repository.keymetest.KeymeTestResultRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeymeTestResultRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : KeymeTestResultRepository {

    override suspend fun getStatistics(questionId: String): KeymeTestResultStatisticsResponse {
        return keymeApi.getKeymeTestResultStatistics(questionId = questionId)
    }
}