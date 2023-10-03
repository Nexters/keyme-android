package com.keyme.data.remote.repositoryimpl

import android.util.Base64
import com.keyme.data.remote.datasource.SignInDataSource
import com.keyme.domain.entity.response.SignInResponse
import com.keyme.domain.entity.response.UpdateMemberResponse
import com.keyme.domain.entity.response.UploadProfileImageResponse
import com.keyme.domain.entity.response.VerifyNicknameResponse
import com.keyme.domain.repository.SignInRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
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

    override suspend fun verifyNickname(
        nickname: String,
    ): VerifyNicknameResponse {
        return signInDataSource.verifyNickname(
            nickname = nickname,
        )
    }

    override suspend fun uploadProfileImage(
        imageString: String,
    ): UploadProfileImageResponse {
        val image = Base64.decode(imageString, Base64.DEFAULT)
        val imageRequestBody = image.toRequestBody("image/jpeg".toMediaTypeOrNull())
        val multipartImage = MultipartBody.Part.createFormData("image", "image.jpeg", imageRequestBody)

        return signInDataSource.uploadProfileImage(
            multipartImage = multipartImage,
        )
    }

    override suspend fun updateMember(
        nickname: String,
        profileImage: String,
        profileThumbnail: String,
    ): UpdateMemberResponse {
        return signInDataSource.updateMember(
            nickname = nickname,
            profileImage = profileImage,
            profileThumbnail = profileThumbnail,
        )
    }
}
