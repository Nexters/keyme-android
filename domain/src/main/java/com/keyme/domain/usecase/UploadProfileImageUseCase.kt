package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.UploadProfileImage
import com.keyme.domain.repository.SignInRepository
import javax.inject.Inject

class UploadProfileImageUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(
        imageString: String,
    ): ApiResult<UploadProfileImage> {
        return apiResult {
            signInRepository.uploadProfileImage(
                imageString = imageString,
            )
        }
    }
}
