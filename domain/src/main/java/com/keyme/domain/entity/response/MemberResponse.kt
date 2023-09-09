package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class MemberResponse : BaseResponse<Member>()

data class Member(
    val friendCode: String?,
    val id: Int,
    val isOnboardingClear: Boolean,
    val nickname: String,
    val profileImage: String,
    val profileThumbnail: String,
)

fun Member.toMember(accessToken: String): com.keyme.domain.entity.member.Member {
    return com.keyme.domain.entity.member.Member(
        friendCode = friendCode ?: "",
        id = id,
        isOnboardingClear = isOnboardingClear,
        nickname = nickname,
        profileImage = profileImage,
        profileThumbnail = profileThumbnail,
        accessToken = accessToken,
    )
}
