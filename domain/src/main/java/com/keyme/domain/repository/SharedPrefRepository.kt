package com.keyme.domain.repository

interface SharedPrefRepository {
    fun getPushTokenSavedState(): Boolean
    fun setPushTokenSavedState(isSaved: Boolean)
}
