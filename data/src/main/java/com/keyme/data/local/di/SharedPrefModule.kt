package com.keyme.data.local.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context,
    ): SharedPreferences {
        return context.getSharedPreferences(
            "KeymeSharedPref",
            Context.MODE_PRIVATE,
        )
    }
}
