package com.keyme.data.remote.repositoryimpl

import com.keyme.data.local.datasource.MyMemberInfoDataSource
import com.keyme.domain.entity.member.Member
import com.keyme.domain.repository.MyMemberInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyMemberInfoRepositoryImpl @Inject constructor(
    private val myMemberInfoDataSource: MyMemberInfoDataSource,
): MyMemberInfoRepository {
    override fun getInfo(): Flow<Member?> = myMemberInfoDataSource.myMemberInfoFlow

    override suspend fun updateInfo(member: Member) {
        myMemberInfoDataSource.setMyMemberInfo(member)
    }

    override suspend fun clearInfo(member: Member) {
        myMemberInfoDataSource.clear()
    }
}
