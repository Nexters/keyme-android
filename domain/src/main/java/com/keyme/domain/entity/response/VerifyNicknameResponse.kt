package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class VerifyNicknameResponse : BaseResponse<VerifyNickname>()

data class VerifyNickname(
    val description: String,
    val valid: Boolean,
)
