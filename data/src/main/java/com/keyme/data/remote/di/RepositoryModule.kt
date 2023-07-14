package com.keyme.data.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.keyme.data.remote.repositoryimpl.SampleRepositoryImpl
import com.keyme.domain.repository.SampleRepository


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSampleRepository(impl: SampleRepositoryImpl): SampleRepository
}