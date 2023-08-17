package com.keyme.domain.repository

import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.entity.response.UpdateMemberResponse
import com.keyme.domain.entity.response.UploadProfileImageResponse
import com.keyme.domain.entity.response.VerifyNicknameResponse

interface SignInRepository {
    suspend fun signInWithKakao(
        token: String,
    ): SignInResponse

    suspend fun verifyNickname(
        nickname: String,
    ): VerifyNicknameResponse

    suspend fun uploadProfileImage(
        imageString: String,
    ): UploadProfileImageResponse

    suspend fun updateMember(
        nickname: String,
        profileImage: String,
        profileThumbnail: String
    ): UpdateMemberResponse
}
