package com.keyme.data.local.di

import android.app.Application
import androidx.room.Room
import com.keyme.data.local.dao.UserAuthDao
import com.keyme.data.local.database.KeymeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideUserAuthDao(database: KeymeDatabase): UserAuthDao {
        return database.userAuthDao()
    }

    @Provides
    @Singleton
    fun provideKeymeDatabase(application: Application): KeymeDatabase {
        return Room.databaseBuilder(
            application,
            KeymeDatabase::class.java,
            "keyme_database",
        ).build()
    }
}
