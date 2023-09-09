package com.keyme.data.local.repositoryimpl

import com.keyme.data.local.datasource.UserDataSource
import com.keyme.domain.entity.room.User
import com.keyme.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override fun getUser(): Flow<User?> = userDataSource.userFlow

    override suspend fun updateUser(user: User) {
        userDataSource.setUser(user)
    }

    override suspend fun clearUser(user: User) {
        userDataSource.clear()
    }
}
