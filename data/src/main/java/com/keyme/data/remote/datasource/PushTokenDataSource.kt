package com.keyme.data.remote.datasource

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.BaseResponseWithoutData
import com.keyme.domain.entity.request.InsertPushTokenRequest
import javax.inject.Inject

class PushTokenDataSource @Inject constructor(
    private val keymeApi: KeymeApi,
) {
    suspend fun insertPushToken(token: String): BaseResponseWithoutData {
        return keymeApi.insertPushToken(
            InsertPushTokenRequest(token = token),
        )
    }
}
