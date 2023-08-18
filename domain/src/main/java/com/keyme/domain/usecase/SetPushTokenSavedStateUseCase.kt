package com.keyme.domain.usecase

import com.keyme.domain.repository.SharedPrefRepository
import javax.inject.Inject

class SetPushTokenSavedStateUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository,
) {
    operator fun invoke(isSaved: Boolean) {
        return sharedPrefRepository.setPushTokenSavedState(isSaved)
    }
}
