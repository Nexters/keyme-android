package com.keyme.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userAuth")
data class UserAuthEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val nickname: String?,
    val profileImage: String?,
    val profileThumbnail: String?,
    val friendCode: String?,
    val onboardingTestResultId: Int?,
    val accessToken: String,
)
