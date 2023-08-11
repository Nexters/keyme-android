package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class SignInResponse : BaseResponse<SignInResult>()

data class SignInResult(
    val memberId: Int,
    val nickname: String?,
    val profileImage: String,
    val profileThumbnail: String,
    val friendCode: String,
    val onboardingTestResultId: Int?,
    val token: Token,
) {
    data class Token(
        val accessToken: String,
    )
}
