package com.keyme.domain.repository

import com.keyme.domain.entity.room.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<User?>
    suspend fun updateUser(user: User)
    suspend fun clearUser(user: User)
}
