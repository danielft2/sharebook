package com.example.sharebook.core.di

import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideValidateRequiredUseCase() : ValidateRequiredUseCase {
        return ValidateRequiredUseCase()
    }
}