package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class SolvedScoreListResponse: BaseResponse<SolvedScoreList>()

data class SolvedScoreList(
    val count: Int,
    val results: List<SolvedScore>,
)

data class SolvedScore(
    val createAt: String,
    val score: Int,
)
