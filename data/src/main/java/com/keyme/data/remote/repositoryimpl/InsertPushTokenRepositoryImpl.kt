package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.datasource.PushTokenDataSource
import com.keyme.domain.entity.response.InsertPushTokenResponse
import com.keyme.domain.repository.InsertPushTokenRepository
import javax.inject.Inject

class InsertPushTokenRepositoryImpl @Inject constructor(
    private val pushTokenDataSource: PushTokenDataSource,
) : InsertPushTokenRepository {

    override suspend fun insertPushToken(token: String): InsertPushTokenResponse {
        return pushTokenDataSource.insertPushToken(token)
    }
}
