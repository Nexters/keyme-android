package com.keyme.domain.entity.room

data class UserAuth(
    val id: Int,
    val nickname: String?,
    val profileImage: String?,
    val profileThumbnail: String?,
    val friendCode: String?,
    val onboardingTestResultId: Int?,
    val accessToken: String,
)
