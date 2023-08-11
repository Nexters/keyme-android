package com.keyme.domain.repository

import com.keyme.domain.entity.response.SignInResponse

interface SignInRepository {
    suspend fun signInWithKakao(
        token: String,
    ): SignInResponse
}
