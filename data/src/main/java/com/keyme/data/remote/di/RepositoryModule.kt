package com.keyme.data.remote.di

import com.keyme.data.remote.repositoryimpl.InsertPushTokenRepositoryImpl
import com.keyme.data.remote.repositoryimpl.KeymeTestRepositoryImpl
import com.keyme.data.remote.repositoryimpl.MemberRepositoryImpl
import com.keyme.data.remote.repositoryimpl.QuestionRepositoryImpl
import com.keyme.data.remote.repositoryimpl.SignInRepositoryImpl
import com.keyme.domain.repository.InsertPushTokenRepository
import com.keyme.domain.repository.KeymeTestRepository
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.SignInRepository
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
    abstract fun bindMemberRepository(impl: MemberRepositoryImpl): MemberRepository

    @Binds
    abstract fun bindQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository

    @Binds
    abstract fun bindKeymeTestRepository(impl: KeymeTestRepositoryImpl): KeymeTestRepository
}
