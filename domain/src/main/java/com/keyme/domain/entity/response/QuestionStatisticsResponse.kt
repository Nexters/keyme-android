package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class QuestionStatisticsResponse : BaseResponse<Question>()

data class Question(
    val avgScore: Int,
    val category: Category,
    val keyword: String,
    val questionId: Int,
    val title: String,
)
