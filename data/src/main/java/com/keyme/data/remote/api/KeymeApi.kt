package com.keyme.data.remote.api

import com.keyme.domain.entity.response.SampleResponse
import retrofit2.http.GET

interface KeymeApi {

    @GET("main/sample")
    suspend fun getSample(): SampleResponse
}
