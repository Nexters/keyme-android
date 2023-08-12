package com.keyme.domain.entity.response.keymetest

import com.keyme.domain.entity.BaseResponse

class KeymeTestResultStatisticsResponse : BaseResponse<KeymeTestResultStatistics>()

data class KeymeTestResultStatistics(
    val averageRate: Int,
    val questionsStatistics: List<QuestionsStatistic>,
    val solvedCount: Int,
)

data class Category(
    val color: String,
    val imageUrl: String,
    val name: String,
)

data class QuestionsStatistic(
    val averageScore: Int,
    val category: Category,
    val description: String,
    val keyword: String,
    val questionId: Int,
)
