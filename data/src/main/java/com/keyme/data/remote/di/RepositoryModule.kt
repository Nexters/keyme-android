package com.keyme.data.remote.di

import com.keyme.data.remote.repositoryimpl.MemberRepositoryImpl
import com.keyme.data.remote.repositoryimpl.QuestionRepositoryImpl
import com.keyme.data.remote.repositoryimpl.SignInRepositoryImpl
import com.keyme.domain.repository.MemberRepository
import com.keyme.domain.repository.QuestionRepository
import com.keyme.domain.repository.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSignInRepository(impl: SignInRepositoryImpl): SignInRepository

    @Binds
    abstract fun bindMemberRepository(impl: MemberRepositoryImpl): MemberRepository

    @Binds
    abstract fun bindQuestionRepository(impl: QuestionRepositoryImpl): QuestionRepository
}
