package com.keyme.domain.usecase

import com.keyme.domain.entity.member.Member
import com.keyme.domain.repository.MyMemberInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyUserInfoUseCase @Inject constructor(
    private val userRepository: MyMemberInfoRepository,
) {
    operator fun invoke(): Flow<Member?> = userRepository.getInfo()
}
