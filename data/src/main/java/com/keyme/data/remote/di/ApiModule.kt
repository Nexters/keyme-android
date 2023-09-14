package com.keyme.data.remote.di

import com.keyme.data.BuildConfig
import com.keyme.data.remote.api.KeymeApi
import com.keyme.domain.usecase.GetMyUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
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
    fun provideKeymeApi(
        getMyUserInfoUseCase: GetMyUserInfoUseCase,
    ): KeymeApi {
        return getRetrofit(getMyUserInfoUseCase).create()
    }

    private fun getRetrofit(
        getMyUserInfoUseCase: GetMyUserInfoUseCase,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.keyme.space")
            .client(getOkHttpClient(getMyUserInfoUseCase))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(
        getMyUserInfoUseCase: GetMyUserInfoUseCase,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val origin = chain.request()
            val requestBuilder = origin.newBuilder()
                .method(origin.method, origin.body)
                .setAuthorizationHeader(origin, getMyUserInfoUseCase)
                .setContentTypeHeader()

            chain.proceed(requestBuilder.build())
        }

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
        }

        return builder.build()
    }

    private fun Request.Builder.setAuthorizationHeader(
        origin: Request,
        getMyUserInfoUseCase: GetMyUserInfoUseCase,
    ): Request.Builder {
        return if (!origin.url.toString().contains("/auth/login")) {
            val token = runBlocking {
                getMyUserInfoUseCase().first()?.accessToken
            }
            this.header("Authorization", "Bearer $token")
        } else {
            this
        }
    }

    private fun Request.Builder.setContentTypeHeader(): Request.Builder {
        return if (this.toString().contains("/images")) {
            this.header("Content-Type", "multipart/form-data;charset=UTF-8")
        } else {
            this.header("Content-Type", "application/json;charset=UTF-8")
        }
    }
}
