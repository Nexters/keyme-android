package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.datasource.SignInDataSource
import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource,
) : SignInRepository {

    override suspend fun signInWithKakao(
        token: String,
    ): SignInResponse {
        return signInDataSource.signInWithKakao(
            token = token,
        )
    }
}
