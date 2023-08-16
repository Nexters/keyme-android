package com.keyme.domain.usecase

import com.keyme.domain.entity.room.UserAuth
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAuthUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
) {
    fun getUserAuth(): Flow<UserAuth?> {
        return userAuthRepository.getUserAuth()
    }
}
