package com.keyme.data.remote.repositoryimpl

import com.keyme.data.remote.datasource.ResultCircleDataSource
import com.keyme.domain.entity.response.ResultCircleResponse
import com.keyme.domain.repository.ResultCircleRepository
import javax.inject.Inject

class ResultCircleRepositoryImpl @Inject constructor(
    private val resultCircleDataSource: ResultCircleDataSource,
) : ResultCircleRepository {

    override suspend fun getDummy(): ResultCircleResponse {
        return resultCircleDataSource.getSample().apply {
            code = "200"
        }
    }
}
