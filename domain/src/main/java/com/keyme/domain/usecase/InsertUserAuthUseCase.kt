package com.keyme.domain.usecase

import com.keyme.domain.entity.room.UserAuth
import com.keyme.domain.repository.UserAuthRepository
import javax.inject.Inject

class InsertUserAuthUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
) {
    suspend operator fun invoke(
        userAuth: UserAuth,
    ) {
        return userAuthRepository.updateUserAuth(userAuth)
    }
}
