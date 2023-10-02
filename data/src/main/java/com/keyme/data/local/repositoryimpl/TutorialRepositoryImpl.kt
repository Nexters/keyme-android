package com.keyme.data.local.repositoryimpl

import com.keyme.data.local.datasource.TutorialDataSource
import com.keyme.domain.repository.TutorialRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TutorialRepositoryImpl @Inject constructor(
    private val tutorialDataSource: TutorialDataSource,
): TutorialRepository {
    override fun isLearned(): Flow<Boolean> {
        return tutorialDataSource.isLearned
    }

    override fun setLearned(value: Boolean) {
        tutorialDataSource.setLearned(value)
    }
}
