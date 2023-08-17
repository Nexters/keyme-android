package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.InsertPushTokenRepository
import javax.inject.Inject

class InsertPushTokenUseCase @Inject constructor(
    private val insertPushTokenRepository: InsertPushTokenRepository,
) {
    suspend operator fun invoke(token: String): ApiResult<Any> {
        return apiResult {
            insertPushTokenRepository.insertPushToken(token)
        }
    }
}
