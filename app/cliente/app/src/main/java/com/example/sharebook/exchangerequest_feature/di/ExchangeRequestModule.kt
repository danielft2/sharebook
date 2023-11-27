package com.example.sharebook.exchangerequest_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.exchangerequest_feature.data.remote.repository.ExchangeRequestRepositoryImpl
import com.example.sharebook.exchangerequest_feature.data.remote.service.ExchangeRequestService
import com.example.sharebook.exchangerequest_feature.domain.adapter.ExchangeRequestRepository
import com.example.sharebook.exchangerequest_feature.domain.usecases.SendRequestUseCase
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
object ExchangeRequestModule {

    @Provides
    @Singleton
    fun provideExchangeRequestService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): ExchangeRequestService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ExchangeRequestService::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRequestRepository(
        exchangeRequestService: ExchangeRequestService): ExchangeRequestRepository {
        return ExchangeRequestRepositoryImpl(exchangeRequestService)
    }

    @Provides
    @Singleton
    fun provideSendRequestUseCase(
        exchangeRequestRepository: ExchangeRequestRepository): SendRequestUseCase {
        return SendRequestUseCase(exchangeRequestRepository)
    }
}