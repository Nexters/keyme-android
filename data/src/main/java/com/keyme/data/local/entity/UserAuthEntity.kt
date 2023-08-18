package com.keyme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userAuth")
data class UserAuthEntity(
    val id: Int = 0,
    val nickname: String?,
    val profileImage: String?,
    val profileThumbnail: String?,
    val friendCode: String?,
    val onboardingTestResultId: Int?,
    @PrimaryKey val accessToken: String,
)
