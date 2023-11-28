package com.example.sharebook.core.di

import com.example.sharebook.core.data.remote.respository.IsbnRepositoryImpl
import com.example.sharebook.core.data.remote.service.IsbnService
import com.example.sharebook.core.domain.adapter.IsbnRepository
import com.example.sharebook.core.domain.usecase.ConsultIsbnUseCase
import com.example.sharebook.core.utils.Constants
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
class IsbnModule {

    @Provides
    @Singleton
    fun provideIsbnService(): IsbnService {
        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_BOOKS_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IsbnService::class.java)
    }

    @Provides
    @Singleton
    fun providesIsbnRepository(isbnService: IsbnService): IsbnRepository {
        return IsbnRepositoryImpl(isbnService)
    }

    @Provides
    @Singleton
    fun provideConsultIsbnUseCase(isbnRepository: IsbnRepository): ConsultIsbnUseCase {
        return ConsultIsbnUseCase(isbnRepository)
    }
}