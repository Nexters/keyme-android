package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class SignInResponse : BaseResponse<SignInResult>()

data class SignInResult(
    val friendCode: String?,
    val id: Int,
    val isOnboardingClear: Boolean,
    val nickname: String?,
    val profileImage: String,
    val profileThumbnail: String,
    val token: Token,
) {
    data class Token(
        val accessToken: String,
    )
}
