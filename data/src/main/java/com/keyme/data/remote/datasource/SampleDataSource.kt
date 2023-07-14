package com.keyme.data.remote.datasource

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.response.SampleResponse
import javax.inject.Inject


class SampleDataSource @Inject constructor(
    private val keymeApi: KeymeApi
) {
    suspend fun getSample(): SampleResponse {
        return keymeApi.getSample()
    }
}