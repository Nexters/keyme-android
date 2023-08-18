package com.keyme.domain.entity.request

data class UpdateMemberRequest(
    val nickname: String,
    val profileImage: String,
    val profileThumbnail: String,
)
