package com.keyme.domain.repository

import com.keyme.domain.entity.response.QuestionSolvedScoreListResponse
import com.keyme.domain.entity.response.QuestionSolvedScoreResponse
import com.keyme.domain.entity.response.QuestionStatisticsResponse

interface QuestionRepository {
    suspend fun getSolvedScore(questionId: String, ownerId: String): QuestionSolvedScoreResponse

    suspend fun getStatistics(questionId: String): QuestionStatisticsResponse

    suspend fun getSolvedScoreList(
        cursor: Int?,
        questionId: String,
        limit: Int = 20,
        ownerId: Int,
    ): QuestionSolvedScoreListResponse
}
