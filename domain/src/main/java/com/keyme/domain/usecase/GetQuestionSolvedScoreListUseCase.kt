package com.keyme.domain.usecase

import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class GetQuestionSolvedScoreListUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    operator fun invoke(questionId: String, ownerId: Int) =
        questionRepository.getSolvedScoreList(questionId = questionId, ownerId = ownerId)
}
