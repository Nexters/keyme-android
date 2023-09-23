package com.keyme.domain.repository

import com.keyme.domain.entity.member.Member
import com.keyme.domain.entity.response.EmptyResponse
import kotlinx.coroutines.flow.Flow

interface MyMemberInfoRepository {

    fun getInfo(): Flow<Member?>
    suspend fun updateInfo(member: Member)

    suspend fun clearInfo()
}
