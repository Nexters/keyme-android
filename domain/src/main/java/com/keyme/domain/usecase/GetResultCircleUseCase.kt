package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.Circle
import com.keyme.domain.repository.ResultCircleRepository
import javax.inject.Inject

class GetResultCircleUseCase @Inject constructor(
    private val resultCircleRepository: ResultCircleRepository,
) {
    suspend operator fun invoke(): ApiResult<List<Circle>> {
        return apiResult {
            resultCircleRepository.getDummy()
        }
    }
}
