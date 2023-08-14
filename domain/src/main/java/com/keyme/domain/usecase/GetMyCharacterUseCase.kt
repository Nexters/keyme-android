package com.keyme.domain.usecase

import com.keyme.domain.entity.member.Member
import javax.inject.Inject

class GetMyCharacterUseCase @Inject constructor() {
    operator fun invoke(): Member {
        return Member(friendCode = "", id = 1, nickname = "키미미미", profileImage = "", profileThumbnail = "")
    }
}
