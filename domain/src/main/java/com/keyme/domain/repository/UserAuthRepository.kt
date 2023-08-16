package com.keyme.domain.repository

import com.keyme.domain.entity.room.UserAuth
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {

    fun getUserAuth(): Flow<UserAuth?>
    suspend fun updateUserAuth(userAuth: UserAuth)
    suspend fun clearUserAuth(userAuth: UserAuth)
}
