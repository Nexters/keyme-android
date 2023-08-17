package com.keyme.domain.repository

import com.keyme.domain.entity.response.InsertPushTokenResponse

interface InsertPushTokenRepository {

    suspend fun insertPushToken(token: String): InsertPushTokenResponse
}
