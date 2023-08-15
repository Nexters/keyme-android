package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class MemberStatisticsResponse : BaseResponse<MemberStatistics>()

data class MemberStatistics(
    val memberId: Int = 0,
    val results: List<Result> = listOf(),
) {
    enum class StatisticsType {
        SIMILAR, DIFFERENT
    }
}

data class Result(
    val coordinate: Coordinate,
    val questionStatistic: QuestionStatistic,
)

data class Coordinate(
    val r: Double,
    val x: Double,
    val y: Double,
)
