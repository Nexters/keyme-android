package com.keyme.domain.usecase

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.member.toMember
import com.keyme.domain.repository.UserRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetMyCharacterUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke() = userRepository
        .getUser()
        .onStart { Member.EMPTY }
        .filterNotNull()
        .map { it.toMember() }
        .filter { it != Member.EMPTY }
        .distinctUntilChanged()
}
