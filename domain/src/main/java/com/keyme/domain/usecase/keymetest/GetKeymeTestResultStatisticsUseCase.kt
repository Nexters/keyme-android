package com.keyme.domain.usecase.keymetest

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatistics
import com.keyme.domain.repository.keymetest.KeymeTestResultRepository
import javax.inject.Inject

class GetKeymeTestResultStatisticsUseCase @Inject constructor(
    private val keymeTestResultRepository: KeymeTestResultRepository,
) {
    suspend operator fun invoke(questionId: String): ApiResult<KeymeTestResultStatistics> {
        return apiResult {
            keymeTestResultRepository.getStatistics(questionId)
        }
    }
}
