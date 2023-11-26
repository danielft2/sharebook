package com.example.sharebook.core.di

import com.example.sharebook.core.data.remote.interceptor.TokenInterceptor
import com.example.sharebook.core.domain.adapter.TokenStorageManagement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideTokenInterceptor(tokenStorageManagement: TokenStorageManagement) : TokenInterceptor {
        return TokenInterceptor(tokenStorageManagement)
    }

    @Provides
    @Singleton
    fun provideOkHttpClientAuth(tokenInterceptor: TokenInterceptor): OkHttpClient {
       return OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    suspend fun provideAcessToken(tokenStorageManagement: TokenStorageManagement): String {
        var accessToken = ""
        tokenStorageManagement.retry().collect {
            if (it != null) accessToken = it
        }

        return accessToken
    }
}