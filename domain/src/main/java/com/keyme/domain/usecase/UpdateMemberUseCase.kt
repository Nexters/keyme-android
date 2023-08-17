package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.UpdateMember
import com.keyme.domain.repository.SignInRepository
import javax.inject.Inject

class UpdateMemberUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(
        nickname: String,
        profileImage: String,
        profileThumbnail: String
    ): ApiResult<UpdateMember> {
        return apiResult {
            signInRepository.updateMember(
                nickname = nickname,
                profileImage = profileImage,
                profileThumbnail = profileThumbnail
            )
        }
    }
}
