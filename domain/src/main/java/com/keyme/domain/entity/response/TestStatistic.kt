package com.keyme.domain.entity.response

data class TestStatistic(
    val averageRate: Float,
    val solvedCount: Int,
    val questionsStatistics: List<QuestionStatistic>,
)
