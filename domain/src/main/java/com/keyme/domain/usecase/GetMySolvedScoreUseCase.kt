package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetMySolvedScoreUseCase @Inject constructor(
    private val userAuthRepository: UserAuthRepository,
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String): ApiResult<QuestionSolvedScore> {
        return apiResult {
            val ownerId = userAuthRepository.getUserAuth().firstOrNull()?.id ?: 0
            questionRepository.getSolvedScore(
                questionId = questionId,
                ownerId = ownerId.toString(),
            )
        }
    }
}
