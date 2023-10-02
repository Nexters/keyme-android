package com.keyme.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.keyme.data.local.di.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class TutorialDataSource @Inject constructor(
    @ApplicationContext context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
) {
    private val dataStore = context.dataStore

    val isLearned = dataStore.data.map { it[KEY_TUTORIAL_LEARNED] ?: false }

    fun setLearned(value: Boolean) {
        applicationScope.launch {
            dataStore.edit {
                it[KEY_TUTORIAL_LEARNED] = value
            }
        }
    }

    companion object {
        private val KEY_TUTORIAL_LEARNED = booleanPreferencesKey("tutorial_learned")
    }
}
