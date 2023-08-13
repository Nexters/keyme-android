package com.keyme.data.remote.di

import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.entity.member.Member
import com.keyme.domain.usecase.GetMyCharacterUseCase
import com.keyme.domain.usecase.GetMyMemberTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideKeymeApi(getMyMemberTokenUseCase: GetMyMemberTokenUseCase): KeymeApi {
        return getRetrofit(getMyMemberTokenUseCase()).create()
    }

    private fun getRetrofit(memberToken: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.keyme.space")
            .client(getOkHttpClient(memberToken))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(memberToken: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Authorization", "Bearer $memberToken")
                    .build()
                    .let(chain::proceed)
            }
            .build()
    }
}
