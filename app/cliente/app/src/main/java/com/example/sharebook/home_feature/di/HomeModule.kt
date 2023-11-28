package com.example.sharebook.home_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.home_feature.data.remote.repository.HomeRepositoryImpl
import com.example.sharebook.home_feature.data.remote.service.HomeService
import com.example.sharebook.home_feature.domain.adpater.HomeRepository
import com.example.sharebook.home_feature.domain.usecases.ListBooksUseCase
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
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): HomeService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideListBooksUseCase(homeRepository: HomeRepository): ListBooksUseCase {
        return ListBooksUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeService: HomeService): HomeRepository {
        return HomeRepositoryImpl(homeService)
    }

}