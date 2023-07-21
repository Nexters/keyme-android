package com.keyme.data.remote.di

import com.keyme.data.remote.repositoryimpl.SampleRepositoryImpl
import com.keyme.domain.repository.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSampleRepository(impl: SampleRepositoryImpl): SampleRepository
}
