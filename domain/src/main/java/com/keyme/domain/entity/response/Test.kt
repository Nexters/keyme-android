package com.keyme.domain.entity.response

import com.keyme.domain.entity.member.Member

data class Test(
    val questions: List<Question>,
    val owner: Member,
    val solvedCount: Int,
    val testId: Int,
    val testResultId: Int,
    val title: String,
) {
    companion object {
        val EMPTY = Test(
            questions = listOf(),
            owner = Member(
                friendCode = "",
                id = 0,
                nickname = "",
                profileImage = "",
                profileThumbnail = "",
            ),
            solvedCount = 0, testId = 0, testResultId = 0, title = "",
        )
    }
}
