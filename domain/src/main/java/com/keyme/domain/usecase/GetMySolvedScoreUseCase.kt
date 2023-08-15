package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class GetMySolvedScoreUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String) =
        apiResult {
            questionRepository.getSolvedScore(
                questionId = questionId,
                ownerId = "1",
            )
        }
}
