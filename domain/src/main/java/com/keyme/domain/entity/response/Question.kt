package com.keyme.domain.entity.response

data class Question(
    val avgScore: Int,
    val category: Category,
    val keyword: String,
    val questionId: Int,
    val title: String
)
