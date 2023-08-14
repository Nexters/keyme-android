package com.keyme.domain.usecase

import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.SolvedScore
import com.keyme.domain.repository.QuestionRepository
import javax.inject.Inject

class GetMySolvedScoreUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    operator fun invoke() = SolvedScore("", 3)
}
