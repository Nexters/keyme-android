package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class MemberStatisticsResponse: BaseResponse<MemberStatistics>()

data class MemberStatistics(
    val memberId: Int,
    val results: List<Result>
) {
    enum class StatisticsType {
        SIMILAR, DIFFERENT
    }
}
