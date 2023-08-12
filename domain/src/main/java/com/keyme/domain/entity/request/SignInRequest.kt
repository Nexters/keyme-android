package com.keyme.domain.entity.request

data class SignInRequest(
    val oauthType: String,
    val token: String,
)
