package com.keyme.domain.usecase

import com.keyme.domain.entity.room.User
import com.keyme.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    fun getUserAuth(): Flow<User?> {
        return userRepository.getUser()
    }
}
