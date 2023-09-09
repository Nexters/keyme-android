package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.UserRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetQuestionStatisticsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String) = apiResult {
        val ownerId = userRepository.getUser().filterNotNull().first().id
        questionRepository.getStatistics(questionId, ownerId.toString())
    }
}
