package com.keyme.domain.entity.response

data class TestRegisterResponse(
    val matchRate: Float,
    val resultCode: String,
    val testResultId: Int,
) {
    companion object {
        val EMPTY = TestRegisterResponse(matchRate = 0.0f, resultCode = "", testResultId = 0)
    }
}

fun TestRegisterResponse.isRegister() = this != TestRegisterResponse.EMPTY
