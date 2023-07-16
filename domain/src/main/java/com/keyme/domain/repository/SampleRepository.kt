package com.keyme.domain.repository

import com.keyme.domain.entity.response.SampleResponse

interface SampleRepository {
    suspend fun getSample(): SampleResponse
}
