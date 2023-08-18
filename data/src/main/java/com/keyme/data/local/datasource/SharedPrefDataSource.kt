package com.keyme.data.local.datasource

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    fun getPushTokenSavedState(): Boolean {
        return sharedPreferences.getBoolean("IS_TOKEN_SAVED", false)
    }

    fun setPushTokenSavedState(isSaved: Boolean) {
        return sharedPreferences.edit()
            .putBoolean("IS_TOKEN_SAVED", isSaved)
            .apply()
    }
}
