package com.keyme.data.remote.di

import com.keyme.data.remote.repositoryimpl.InsertPushTokenRepositoryImpl
import com.keyme.data.remote.repositoryimpl.MemberRepositoryImpl
import com.keyme.data.remote.repositoryimpl.QuestionRepositoryImpl
import com.keyme.data.remote.repositoryimpl.ResultCircleRepositoryImpl
import com.keyme.data.remote.repositoryimpl.SignInRepositoryImpl
import com.keyme.data.remote.repositoryimpl.keymetest.KeymeTestResultRepositoryImpl
import com.keyme.domain.repository.InsertPushTokenRepository
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.ResultCircleRepository
import com.keyme.domain.repository.SignInRepository
import com.keyme.domain.repository.keymetest.KeymeTestResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSignInRepository(impl: SignInRepositoryImpl): SignInRepository

    @Binds
    abstract fun bindInsertPushTokenRepository(impl: InsertPushTokenRepositoryImpl): InsertPushTokenRepository

    @Binds
    abstract fun bindResultCircleRepository(impl: ResultCircleRepositoryImpl): ResultCircleRepository

    @Binds
    abstract fun bindKeymeTestResultRepository(impl: KeymeTestResultRepositoryImpl): KeymeTestResultRepository

    @Binds
    abstract fun bindMemberRepository(impl: MemberRepositoryImpl): MemberRepository

    @Binds
    abstract fun bindQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository
}
