package com.keyme.domain.entity.member

import com.keyme.domain.entity.room.UserAuth

data class Member(
    val friendCode: String = "",
    val id: Int = 0,
    val nickname: String = "",
    val profileImage: String = "",
    val profileThumbnail: String = "",
) {
    companion object {
        val EMPTY = Member(friendCode = "", id = 0, nickname = "", profileImage = "", profileThumbnail = "")
    }
}

fun UserAuth.toMember() = Member(
    friendCode = friendCode ?: "",
    id = id,
    nickname = nickname ?: "",
    profileImage = profileImage ?: "",
    profileThumbnail = profileThumbnail ?: "",
)
