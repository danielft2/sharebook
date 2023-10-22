package com.example.sharebook.core.di

import com.example.sharebook.core.data.remote.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideTokenInterceptor() : TokenInterceptor {
        return TokenInterceptor()
    }

    @Provides
    fun provideOkHttpClientAuth(tokenInterceptor: TokenInterceptor): OkHttpClient {
       return OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .build()
    }
}