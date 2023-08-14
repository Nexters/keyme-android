package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionStatisticsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String) = apiResult { questionRepository.getStatistics(questionId) }
}
