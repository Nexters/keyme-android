package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.SignInResult
import com.keyme.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(
        token: String,
    ): ApiResult<SignInResult> {
        return apiResult {
            signInRepository.signInWithKakao(token = token)
        }
    }
}
