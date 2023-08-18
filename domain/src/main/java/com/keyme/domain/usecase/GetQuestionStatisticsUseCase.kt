package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetQuestionStatisticsUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String) = apiResult {
        val ownerId = userAuthRepository.getUserAuth().firstOrNull()?.id ?: 0
        questionRepository.getStatistics(questionId, ownerId.toString())
    }
}
