package com.keyme.domain.usecase

import com.keyme.domain.entity.ApiResult
import com.keyme.domain.entity.apiResult
import com.keyme.domain.entity.response.QuestionSolvedScore
import com.keyme.domain.repository.MyMemberInfoRepository
import com.keyme.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetMySolvedScoreUseCase @Inject constructor(
    private val userRepository: MyMemberInfoRepository,
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke(questionId: String): ApiResult<QuestionSolvedScore> {
        return apiResult {
            val ownerId = userRepository.getInfo().filterNotNull().first().id
            questionRepository.getSolvedScore(
                questionId = questionId,
                ownerId = ownerId.toString(),
            )
        }
    }
}
