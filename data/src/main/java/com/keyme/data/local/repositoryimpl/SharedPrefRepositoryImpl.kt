package com.keyme.data.local.repositoryimpl

import com.keyme.data.local.datasource.SharedPrefDataSource
import com.keyme.domain.repository.SharedPrefRepository
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPrefDataSource,
) : SharedPrefRepository {

    override fun getPushTokenSavedState(): Boolean {
        return sharedPrefDataSource.getPushTokenSavedState()
    }

    override fun setPushTokenSavedState(isSaved: Boolean) {
        return sharedPrefDataSource.setPushTokenSavedState(isSaved)
    }
}
