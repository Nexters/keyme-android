package com.keyme.data.local.repositoryimpl

import com.keyme.data.local.dao.UserAuthDao
import com.keyme.data.local.entity.UserAuthEntity
import com.keyme.domain.entity.room.UserAuth
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val userAuthDao: UserAuthDao,
) : UserAuthRepository {
    override fun getUserAuth(): Flow<UserAuth?> {
        return userAuthDao.getUserAuth().map {
            it?.let {
                UserAuth(
                    id = it.id,
                    nickname = it.nickname,
                    profileImage = it.profileImage,
                    profileThumbnail = it.profileThumbnail,
                    friendCode = it.friendCode,
                    onboardingTestResultId = it.onboardingTestResultId,
                    accessToken = it.accessToken,
                )
            }
        }
    }

    override suspend fun updateUserAuth(userAuth: UserAuth) {
        userAuthDao.insertOrUpdateUserAuth(
            UserAuthEntity(
                id = userAuth.id,
                nickname = userAuth.nickname,
                profileImage = userAuth.profileImage,
                profileThumbnail = userAuth.profileThumbnail,
                friendCode = userAuth.friendCode,
                onboardingTestResultId = userAuth.onboardingTestResultId,
                accessToken = userAuth.accessToken,
            ),
        )
    }

    override suspend fun clearUserAuth(userAuth: UserAuth) {
        userAuthDao.deleteUserAuth(
            UserAuthEntity(
                id = userAuth.id,
                nickname = userAuth.nickname,
                profileImage = userAuth.profileImage,
                profileThumbnail = userAuth.profileThumbnail,
                friendCode = userAuth.friendCode,
                onboardingTestResultId = userAuth.onboardingTestResultId,
                accessToken = userAuth.accessToken,
            ),
        )
    }
}
