package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.KeymeTestRepository
import javax.inject.Inject

class GetOnboardingKeymeTestUseCase @Inject constructor(
    private val keymeTestRepository: KeymeTestRepository,
) {
    suspend operator fun invoke() = apiResult { keymeTestRepository.getOnBoardingTest() }
}
