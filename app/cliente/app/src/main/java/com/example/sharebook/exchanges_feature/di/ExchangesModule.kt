package com.example.sharebook.exchanges_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.exchanges_feature.data.remote.repository.ExchangesRepositoryImpl
import com.example.sharebook.exchanges_feature.data.remote.service.ExchangesService
import com.example.sharebook.exchanges_feature.domain.adapter.ExchangesRepository
import com.example.sharebook.exchanges_feature.domain.usecases.DeleteMyBookUseCase
import com.example.sharebook.exchanges_feature.domain.usecases.ListMyBooksUseCase
import com.example.sharebook.exchanges_feature.domain.usecases.RequestDetailsUseCase
import com.example.sharebook.exchanges_feature.domain.usecases.UpdateStatusRequestUseCase
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
object ExchangesModule {

    @Provides
    @Singleton
    fun provideExchangesService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): ExchangesService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ExchangesService::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangesRepository(exchangesService: ExchangesService): ExchangesRepository {
        return ExchangesRepositoryImpl(exchangesService)
    }

    @Provides
    @Singleton
    fun provideListMyBooksUseCase(exchangesRepository: ExchangesRepository): ListMyBooksUseCase {
        return ListMyBooksUseCase(exchangesRepository)
    }

    @Provides
    @Singleton
    fun provideRequestDetailsUseCase(exchangesRepository: ExchangesRepository): RequestDetailsUseCase {
        return RequestDetailsUseCase(exchangesRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMyBookUseCase(exchangesRepository: ExchangesRepository): DeleteMyBookUseCase {
        return DeleteMyBookUseCase(exchangesRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateStatusRequestUseCase(
        exchangesRepository: ExchangesRepository
    ): UpdateStatusRequestUseCase {
        return UpdateStatusRequestUseCase(exchangesRepository)
    }
}