package com.keyme.data.local.di

import com.keyme.data.local.repositoryimpl.SharedPrefRepositoryImpl
import com.keyme.data.local.repositoryimpl.UserAuthRepositoryImpl
import com.keyme.domain.repository.SharedPrefRepository
import com.keyme.domain.repository.UserAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserAuthRepository(impl: UserAuthRepositoryImpl): UserAuthRepository

    @Binds
    abstract fun bindSharedPrefRepository(impl: SharedPrefRepositoryImpl): SharedPrefRepository
}
