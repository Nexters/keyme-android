package com.keyme.domain.repository

import com.keyme.domain.entity.response.QuestionStatisticsResponse
import com.keyme.domain.entity.response.SolvedScoreListResponse

interface QuestionRepository {
//    suspend fun getMyScore(ownerId: String): MyQuestionScoreResponse

    suspend fun getStatistics(questionId: String): QuestionStatisticsResponse

    suspend fun getSolvedScoreList(cursor: Int?, questionId: String, limit: Int = 20, ownerId: Int): SolvedScoreListResponse
}
