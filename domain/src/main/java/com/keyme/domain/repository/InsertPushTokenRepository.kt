package com.keyme.domain.repository

import com.keyme.domain.entity.BaseResponseWithoutData

interface InsertPushTokenRepository {

    suspend fun insertPushToken(token: String): BaseResponseWithoutData
}
