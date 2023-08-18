package com.keyme.domain.usecase

import com.keyme.domain.repository.KeymeTestRepository
import javax.inject.Inject

class RegistrationResultCodeUseCase @Inject constructor(
    private val keymeTestRepository: KeymeTestRepository,
) {
    suspend operator fun invoke(resultCode: String): Boolean {
        val response = keymeTestRepository.registrationTestResult(resultCode)
        return response.code == "200"
    }
}
