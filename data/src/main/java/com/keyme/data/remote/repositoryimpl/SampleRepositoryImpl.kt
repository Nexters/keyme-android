package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.datasource.SampleDataSource
import com.keyme.domain.entity.response.SampleResponse
import com.keyme.domain.repository.SampleRepository
import javax.inject.Inject


class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource
): SampleRepository {

    override suspend fun getSample(): SampleResponse {
        return sampleDataSource.getSample()
    }
}