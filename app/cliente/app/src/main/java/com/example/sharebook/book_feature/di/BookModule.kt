package com.example.sharebook.book_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.auth_feature.data.remote.service.AuthService
import com.example.sharebook.book_feature.data.remote.service.RequestBookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookModule {

    @Provides
    @Singleton
    fun provideRequestBookService(): RequestBookService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestBookService::class.java)
    }
}