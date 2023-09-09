package com.keyme.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.keyme.data.local.di.ApplicationScope
import com.keyme.domain.entity.room.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
) {
    private val dataStore = context.dataStore

    val userFlow = dataStore.data.map { it[KEY_USER]?.toUser() }

    fun setUser(user: User) {
        applicationScope.launch {
            dataStore.edit {
                it[KEY_USER] = user.toJson()
            }
        }
    }

    private fun String.toUser(): User? {
        return kotlin.runCatching {
            Gson().fromJson(this, User::class.java)
        }.getOrNull()
    }

    private fun User.toJson(): String {
        return kotlin.runCatching { Gson().toJson(this) }.getOrDefault("")
    }

    fun clear() {
        applicationScope.launch {
            dataStore.edit { it[KEY_USER] = "" }
        }
    }

    companion object {
        private val KEY_USER = stringPreferencesKey("user")
    }
}
