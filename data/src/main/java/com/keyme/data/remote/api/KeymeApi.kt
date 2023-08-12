package com.keyme.data.remote.api

import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatisticsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KeymeApi {

    @POST("/auth/login")
    suspend fun signInWithKakao(
        @Body signInRequest: SignInRequest,
    ): SignInResponse

    @GET("tests/{id}/statistics")
    suspend fun getKeymeTestResultStatistics(
        @Path("id") questionId: String,
    ): KeymeTestResultStatisticsResponse
}
