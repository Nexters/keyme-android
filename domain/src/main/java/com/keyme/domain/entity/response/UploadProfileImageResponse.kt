package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class UploadProfileImageResponse : BaseResponse<UploadProfileImage>()

data class UploadProfileImage(
    val originalUrl: String,
    val thumbnailUrl: String,
)
