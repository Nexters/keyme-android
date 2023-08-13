package com.keyme.data.remote.api

import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse
import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatisticsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface KeymeApi {

    @POST("/auth/login")
    suspend fun signInWithKakao(
        @Body signInRequest: SignInRequest,
    ): SignInResponse

    @GET("tests/{id}/statistics")
    suspend fun getKeymeTestResultStatistics(
        @Path("id") questionId: String,
    ): KeymeTestResultStatisticsResponse

    @GET("members/{memberId}/stataistics")
    suspend fun getMemberStatistics(
        @Path("memberId") memberId: String,
        @Query("type") type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse
}
