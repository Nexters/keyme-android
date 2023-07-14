package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse


class SampleResponse: BaseResponse<Sample>()

data class Sample(
    val lists: List<Sample>
) {
    data class Sample(
        val type: String,
        val imageURL: String,
        val linkURL: String? = null
    )
}
