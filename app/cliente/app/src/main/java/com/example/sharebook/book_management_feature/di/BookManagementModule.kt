package com.example.sharebook.book_management_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.book_management_feature.data.remote.repository.BookManagementRepositoryImpl
import com.example.sharebook.book_management_feature.data.remote.service.BookManagementService
import com.example.sharebook.book_management_feature.domain.adapter.BookManagementRepository
import com.example.sharebook.book_management_feature.domain.usecase.AddBookUseCases
import com.example.sharebook.book_management_feature.domain.usecase.validations.ValidateIsbnUseCases
import com.example.sharebook.book_management_feature.domain.usecase.validations.ValidateUseCases
import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase
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
object BookManagementModule {

    @Provides
    @Singleton
    fun provideBookManagementService(): BookManagementService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookManagementService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookManagementRepository(bookManagementService: BookManagementService): BookManagementRepository {
        return BookManagementRepositoryImpl(bookManagementService)
    }

    @Provides
    @Singleton
    fun provideAddBookUseCase(repository: BookManagementRepository): AddBookUseCases {
        return AddBookUseCases(repository)
    }

    @Provides
    @Singleton
    fun provideValidateUseCases(): ValidateUseCases {
        return ValidateUseCases(
            validateIsbnUseCases = ValidateIsbnUseCases(),
            validateRequiredUseCase = ValidateRequiredUseCase()
        )
    }
}