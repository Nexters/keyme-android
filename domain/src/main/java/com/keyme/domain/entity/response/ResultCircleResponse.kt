package com.keyme.domain.entity.response

import com.keyme.domain.entity.BaseResponse

class ResultCircleResponse : BaseResponse<List<Circle>>()

data class Circle(val x: Double, val y: Double, val r: Double, val level: Int, val ex: Map<String, Int>)
