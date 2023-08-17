package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class TestResultResponse : BaseResponse<TestResult>()

data class TestResult(
    val matchRate: Float,
    val results: List<QuestionResult>,
    val testId: Int,
    val testResultId: Int,
)

data class QuestionResult(
    val category: Category,
    val keyword: String,
    val questionId: Int,
    val score: Int,
    val title: String,
)
