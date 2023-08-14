package com.keyme.domain.entity.member

data class Member(
    val friendCode: String,
    val id: Int,
    val nickname: String,
    val profileImage: String,
    val profileThumbnail: String,
) {
    companion object{
        val EMPTY = Member(friendCode = "", id = 0, nickname = "", profileImage = "", profileThumbnail = "")
    }
}
