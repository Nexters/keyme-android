package com.keyme.data.local.di

import com.keyme.data.local.repositoryimpl.SharedPrefRepositoryImpl
import com.keyme.data.local.repositoryimpl.TutorialRepositoryImpl
import com.keyme.data.remote.repositoryimpl.MyMemberInfoRepositoryImpl
import com.keyme.domain.repository.MyMemberInfoRepository
import com.keyme.domain.repository.SharedPrefRepository
import com.keyme.domain.repository.TutorialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMyMemberInfoRepository(impl: MyMemberInfoRepositoryImpl): MyMemberInfoRepository

    @Binds
    abstract fun bindTutorialRepository(impl: TutorialRepositoryImpl): TutorialRepository

    @Binds
    abstract fun bindSharedPrefRepository(impl: SharedPrefRepositoryImpl): SharedPrefRepository
}
