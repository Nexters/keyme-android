package com.keyme.data.remote.di

import com.keyme.data.remote.repositoryimpl.ResultCircleRepositoryImpl
import com.keyme.data.remote.repositoryimpl.SignInRepositoryImpl
import com.keyme.data.remote.repositoryimpl.keymetest.KeymeTestResultRepositoryImpl
import com.keyme.domain.repository.ResultCircleRepository
import com.keyme.domain.repository.SignInRepository
import com.keyme.domain.repository.keymetest.KeymeTestResultRepository
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
    abstract fun bindResultCircleRepository(impl: ResultCircleRepositoryImpl): ResultCircleRepository

    @Binds
    abstract fun bindKeymeTestResultRepository(impl: KeymeTestResultRepositoryImpl): KeymeTestResultRepository
}
