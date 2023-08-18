package com.keyme.domain.usecase

import com.keyme.domain.repository.SharedPrefRepository
import javax.inject.Inject

class GetPushTokenSavedStateUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository,
) {
    operator fun invoke(): Boolean {
        return sharedPrefRepository.getPushTokenSavedState()
    }
}
