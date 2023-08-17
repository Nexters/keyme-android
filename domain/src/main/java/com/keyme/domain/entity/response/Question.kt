package com.keyme.domain.entity.response

open class Question(
    open val questionId: Int,
    open val title: String,
    open val keyword: String,
    open val category: Category,
) {
    companion object {
        val EMPTY = Question(
            category = Category(color = "", iconUrl = "", name = ""),
            keyword = "",
            questionId = 0,
            title = "",
        )
    }
}

data class QuestionStatistic(
    val questionId: Int,
    val title: String,
    val keyword: String,
    val category: Category,
    val avgScore: Float,
) {
    companion object {
        val EMPTY = QuestionStatistic(
            avgScore = 0f,
            category = Category(color = "", iconUrl = "", name = ""),
            keyword = "",
            questionId = 0,
            title = "",
        )
    }
}

data class Category(
    val color: String,
    val iconUrl: String,
    val name: String,
)
