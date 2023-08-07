package com.keyme.domain.repository

import com.keyme.domain.entity.response.ResultCircleResponse

interface ResultCircleRepository {

    suspend fun getDummy(): ResultCircleResponse
}
