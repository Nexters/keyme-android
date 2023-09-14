package com.keyme.domain.entity.member

import com.keyme.domain.entity.room.User

data class Member(
    val friendCode: String = "",
    val id: Int = 0,
    val isOnboardingClear: Boolean = false,
    val nickname: String = "",
    val profileImage: String = "",
    val profileThumbnail: String = "",
    val accessToken: String = "",
) {
    companion object {
        val EMPTY = Member(friendCode = "", id = 0, nickname = "", profileImage = "", profileThumbnail = "")
    }
}

fun User.toMember() = Member(
    friendCode = friendCode ?: "",
    id = id,
    nickname = nickname ?: "",
    profileImage = profileImage ?: "",
    profileThumbnail = profileThumbnail ?: "",
)
