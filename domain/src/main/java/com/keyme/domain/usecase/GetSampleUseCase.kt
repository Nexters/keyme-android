package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.Sample
import com.keyme.domain.repository.SampleRepository
import javax.inject.Inject

class GetSampleUseCase @Inject constructor(
    private val sampleRepository: SampleRepository,
) {
    suspend operator fun invoke(): ApiResult<Sample> {
        return apiResult {
            sampleRepository.getSample()
        }
    }
}
