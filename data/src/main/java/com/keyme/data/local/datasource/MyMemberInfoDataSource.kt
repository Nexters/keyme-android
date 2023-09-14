package com.keyme.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.keyme.data.local.di.ApplicationScope
import com.keyme.domain.entity.member.Member
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyMemberInfoDataSource @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
) {
    private val dataStore = context.dataStore

    val myMemberInfoFlow = dataStore.data.map { it[KEY_MY_MEMBER_INFO]?.toMember() }

    fun setMyMemberInfo(member: Member) {
        applicationScope.launch {
            dataStore.edit {
                it[KEY_MY_MEMBER_INFO] = member.toJson()
            }
        }
    }

    private fun String.toMember(): Member? {
        return kotlin.runCatching {
            Gson().fromJson(this, Member::class.java)
        }.getOrNull()
    }

    private fun Member.toJson(): String {
        return kotlin.runCatching { Gson().toJson(this) }.getOrDefault("")
    }

    fun clear() {
        applicationScope.launch {
            dataStore.edit { it[KEY_MY_MEMBER_INFO] = "" }
        }
    }

    companion object {
        private val KEY_MY_MEMBER_INFO = stringPreferencesKey("my_member_info")
    }
}
