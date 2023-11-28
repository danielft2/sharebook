package com.example.sharebook.discovery_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.discovery_feature.data.remote.repository.SearchBookRepositoryImpl
import com.example.sharebook.discovery_feature.data.remote.service.SearchBookService
import com.example.sharebook.discovery_feature.domain.adapter.SearchBookRepository
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
object SearchBookModule {
    @Provides
    @Singleton
    fun provideSearchBookService(): SearchBookService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchBookService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchBookRepository(searchBookService: SearchBookService): SearchBookRepository {
        return SearchBookRepositoryImpl(searchBookService)
    }

}