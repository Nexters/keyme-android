package com.keyme.data.remote.datasource

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.response.SignInResponse
import javax.inject.Inject

class SignInDataSource @Inject constructor(
    private val keymeApi: KeymeApi,
) {
    suspend fun signInWithKakao(
        token: String,
    ): SignInResponse {
        return keymeApi.signInWithKakao(
            SignInRequest(
                oauthType = "KAKAO",
                token = token,
            ),
        )
    }
}
