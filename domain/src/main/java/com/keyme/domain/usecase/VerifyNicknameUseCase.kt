package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.VerifyNickname
import com.keyme.domain.repository.SignInRepository
import javax.inject.Inject

class VerifyNicknameUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(
        nickname: String,
    ): ApiResult<VerifyNickname> {
        return apiResult {
            signInRepository.verifyNickname(nickname = nickname)
        }
    }
}
