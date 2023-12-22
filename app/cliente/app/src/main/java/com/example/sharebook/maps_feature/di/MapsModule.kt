package com.example.sharebook.maps_feature.di

import com.example.sharebook.home_feature.domain.adpater.HomeRepository
import com.example.sharebook.maps_feature.domain.usecases.DeviceLocationUseCase
import com.example.sharebook.maps_feature.domain.usecases.ListBooksForMapsUseCase
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

    fun provideListBooksForMapsUseCase(homeRepository: HomeRepository): ListBooksForMapsUseCase {
        return ListBooksForMapsUseCase(homeRepository)
    }
}