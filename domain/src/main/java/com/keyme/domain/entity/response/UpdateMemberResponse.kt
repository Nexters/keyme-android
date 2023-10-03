package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class UpdateMemberResponse : BaseResponse<UpdateMember>()

data class UpdateMember(
    val id: Int,
    val friendCode: String?,
    val nickname: String,
    val profileImage: String,
    val profileThumbnail: String,
)
