package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class QuestionSolvedScoreListResponse : BaseResponse<QuestionSolvedScoreList>()

data class QuestionSolvedScoreList(
    val totalCount: Int,
    val hasNext: Boolean,
    val results: List<QuestionSolvedScore>,
)

data class QuestionSolvedScore(
    val id: Int,
    val createAt: String = "",
    val score: Int,
) {
    companion object {
        val EMPTY = QuestionSolvedScore(0, "", 0)
    }
}
