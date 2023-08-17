package com.keyme.data.remote.api

import com.keyme.domain.entity.BaseResponseWithoutData
import com.keyme.domain.entity.request.InsertPushTokenRequest
import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.response.DailyTestResponse
import com.keyme.domain.entity.response.MemberStatistics
import com.keyme.domain.entity.response.MemberStatisticsResponse
import com.keyme.domain.entity.response.OnBoardingTestResponse
import com.keyme.domain.entity.response.QuestionSolvedScoreListResponse
import com.keyme.domain.entity.response.QuestionSolvedScoreResponse
import com.keyme.domain.entity.response.QuestionStatisticsResponse
import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.entity.response.TestStatisticResponse
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

    @POST("members/devices")
    suspend fun insertPushToken(
        @Body insertPushTokenRequest: InsertPushTokenRequest,
    ): BaseResponseWithoutData

    @GET("members/{memberId}/statistics")
    suspend fun getMemberStatistics(
        @Path("memberId") memberId: String,
        @Query("type") type: MemberStatistics.StatisticsType,
    ): MemberStatisticsResponse

    @GET("questions/{id}/statistics")
    suspend fun getQuestionStatistics(
        @Path("id") id: String,
        @Query("ownerId") ownerId: Int,
    ): QuestionStatisticsResponse

    @GET("questions/{id}/score")
    suspend fun getQuestionSolvedScore(
        @Path("id") id: String,
        @Query("ownerId") ownerId: String,
    ): QuestionSolvedScoreResponse

    @GET("questions/{id}/solved-scores")
    suspend fun getQuestionSolvedScoreList(
        @Query("cursor") cursor: Int?,
        @Path("id") id: String,
        @Query("limit") limit: Int = 20,
        @Query("ownerId") ownerId: Int,
    ): QuestionSolvedScoreListResponse

    @GET("tests/onboarding")
    suspend fun getOnBoardingTest(): OnBoardingTestResponse

    @GET("tests/daily")
    suspend fun getDailyTest(): DailyTestResponse

    @GET("tests/{id}/statistics")
    suspend fun getTestStatistic(
        @Path("id") testId: Int,
    ): TestStatisticResponse
}
