package com.keyme.domain.usecase

import com.keyme.domain.entity.room.User
import com.keyme.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserAuthUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        user: User,
    ) {
        return userRepository.updateUser(user)
    }
}
