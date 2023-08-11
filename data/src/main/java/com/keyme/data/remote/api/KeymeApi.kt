package com.keyme.data.remote.api

import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface KeymeApi {

    @POST("/auth/login")
    suspend fun signInWithKakao(
        @Body signInRequest: SignInRequest,
    ): SignInResponse
}
