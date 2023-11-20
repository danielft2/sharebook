package com.example.sharebook.maps_feature.di

import com.example.sharebook.maps_feature.domain.usecases.DeviceLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapsModule {

    @Singleton
    @Provides
    fun provideDeviceUseCase(): DeviceLocationUseCase {
        return DeviceLocationUseCase()
    }
}