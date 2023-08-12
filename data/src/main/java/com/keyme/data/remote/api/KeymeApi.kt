package com.keyme.data.remote.api

import com.keyme.domain.entity.response.SampleResponse
import com.keyme.domain.entity.response.keymetest.KeymeTestResultStatisticsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KeymeApi {

    @GET("main/sample")
    suspend fun getSample(): SampleResponse

    @GET("tests/{id}/statistics")
    suspend fun getKeymeTestResultStatistics(
        @Path("id") questionId: String,
    ): KeymeTestResultStatisticsResponse
}
