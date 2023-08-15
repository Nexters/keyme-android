package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class QuestionSolvedScoreListResponse : BaseResponse<QuestionSolvedScoreList>()

data class QuestionSolvedScoreList(
    val count: Int,
    val results: List<QuestionSolvedScore>,
)

data class QuestionSolvedScore(
    val createAt: String = "",
    val score: Float,
) {
    companion object {
        val EMPTY = QuestionSolvedScore("", 0f)
    }
}
