package com.keyme.domain.repository

import com.keyme.domain.entity.response.WithoutDataResponse

interface InsertPushTokenRepository {

    suspend fun insertPushToken(token: String): WithoutDataResponse
}
