package com.keyme.data.remote.datasource

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.request.SignInRequest
import com.keyme.domain.entity.request.UpdateMemberRequest
import com.keyme.domain.entity.request.VerifyNicknameRequest
import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.entity.response.UpdateMemberResponse
import com.keyme.domain.entity.response.UploadProfileImageResponse
import com.keyme.domain.entity.response.VerifyNicknameResponse
import okhttp3.MultipartBody
import javax.inject.Inject

class SignInDataSource @Inject constructor(
    private val keymeApi: KeymeApi,
) {
    suspend fun signInWithKakao(
        token: String,
    ): SignInResponse {
        return keymeApi.signInWithKakao(
            SignInRequest(
                oauthType = "KAKAO",
                token = token,
            ),
        )
    }

    suspend fun verifyNickname(
        nickname: String,
    ): VerifyNicknameResponse {
        return keymeApi.verifyNickname(
            VerifyNicknameRequest(
                nickname = nickname,
            ),
        )
    }

    suspend fun uploadProfileImage(
        multipartImage: MultipartBody.Part,
    ): UploadProfileImageResponse {
        return keymeApi.uploadProfileImage(
            multipartImage = multipartImage,
        )
    }

    suspend fun updateMember(
        nickname: String,
        profileImage: String,
        profileThumbnail: String,
    ): UpdateMemberResponse {
        return keymeApi.updateMember(
            UpdateMemberRequest(
                nickname = nickname,
                profileImage = profileImage,
                profileThumbnail = profileThumbnail,
            ),
        )
    }
}
