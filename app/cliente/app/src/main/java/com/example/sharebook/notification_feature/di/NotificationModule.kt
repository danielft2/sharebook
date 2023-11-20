package com.example.sharebook.notification_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.discovery_feature.data.remote.service.SearchBookService
import com.example.sharebook.notification_feature.data.remote.repository.NotificationRepositoryImpl
import com.example.sharebook.notification_feature.data.remote.service.NotificationService
import com.example.sharebook.notification_feature.domain.adapter.NotificationRepository
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
object NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationService(): NotificationService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationService::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationService: NotificationService): NotificationRepository {
        return NotificationRepositoryImpl(notificationService)
    }
}