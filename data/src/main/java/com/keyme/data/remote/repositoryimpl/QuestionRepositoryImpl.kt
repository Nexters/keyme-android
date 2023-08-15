package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.QuestionStatisticsResponse
import com.keyme.domain.entity.response.QuestionSolvedScoreListResponse
import com.keyme.domain.entity.response.QuestionSolvedScoreResponse
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : QuestionRepository {
    override suspend fun getSolvedScore(questionId: String, ownerId: String): QuestionSolvedScoreResponse {
        return keymeApi.getQuestionSolvedScore(questionId, ownerId)
    }

    override suspend fun getStatistics(questionId: String): QuestionStatisticsResponse {
        return keymeApi.getQuestionStatistics(id = questionId, ownerId = 1)
    }

    override suspend fun getSolvedScoreList(
        cursor: Int?,
        questionId: String,
        limit: Int,
        ownerId: Int,
    ): QuestionSolvedScoreListResponse {
        return keymeApi.getQuestionSolvedScoreList(cursor, questionId, limit, ownerId)
    }
}
