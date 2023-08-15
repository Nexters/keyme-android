package com.keyme.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.entity.response.QuestionSolvedScoreResponse
import com.keyme.domain.entity.response.QuestionStatisticsResponse
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun getSolvedScore(questionId: String, ownerId: String): QuestionSolvedScoreResponse

    suspend fun getStatistics(questionId: String): QuestionStatisticsResponse

    fun getSolvedScoreList(
        questionId: String,
        limit: Int = 20,
        ownerId: Int,
    ): Pager<Int, QuestionSolvedScore>
}
