package com.keyme.domain.repository

import kotlinx.coroutines.flow.Flow

interface TutorialRepository {
    fun isLearned(): Flow<Boolean>

    fun setLearned(value: Boolean)
}
