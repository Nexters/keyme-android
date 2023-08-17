package com.keyme.data.remote.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.keyme.data.remote.api.KeymeApi
import com.keyme.data.remote.datasource.QuestionSolvedScoreListPagingSource
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.QuestionSolvedScoreResponse
import com.keyme.domain.entity.response.QuestionStatisticsResponse
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val keymeApi: KeymeApi,
) : QuestionRepository {
    override suspend fun getSolvedScore(questionId: String, ownerId: String): QuestionSolvedScoreResponse {
        return keymeApi.getQuestionSolvedScore(questionId, ownerId)
    }

    override suspend fun getStatistics(questionId: String): QuestionStatisticsResponse {
        return keymeApi.getQuestionStatistics(id = questionId, ownerId = 2)
    }

    override fun getSolvedScoreList(
        questionId: String,
        limit: Int,
        ownerId: Int,
    ): Pager<Int, QuestionSolvedScore> {
        return Pager(config = PagingConfig(pageSize = limit)) {
            QuestionSolvedScoreListPagingSource(keymeApi, questionId, ownerId)
        }
    }
}
