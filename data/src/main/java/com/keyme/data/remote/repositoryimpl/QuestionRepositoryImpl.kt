package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.QuestionStatisticsResponse
import com.keyme.domain.entity.response.SolvedScoreListResponse
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
): QuestionRepository {
    override suspend fun getStatistics(questionId: String): QuestionStatisticsResponse {
        return keymeApi.getQuestionStatistics(id = questionId, ownerId = 1)
    }

    override suspend fun getSolvedScoreList(
        cursor: Int?,
        questionId: String,
        limit: Int,
        ownerId: Int,
    ): SolvedScoreListResponse {
        return keymeApi.getSolvedScoreList(cursor, questionId, limit, ownerId)
    }
}
