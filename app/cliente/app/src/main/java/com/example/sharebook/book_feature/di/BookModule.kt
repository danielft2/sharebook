package com.example.sharebook.book_feature.di

import com.example.sharebook.BuildConfig
import com.example.sharebook.book_feature.data.remote.repository.BookRepositoryImpl
import com.example.sharebook.book_feature.data.remote.service.BookService
import com.example.sharebook.book_feature.domain.adapter.BookRepository
import com.example.sharebook.book_feature.domain.usecase.CreateBookUseCase
import com.example.sharebook.book_feature.domain.usecase.DetailsBookUseCase
import com.example.sharebook.book_feature.domain.usecase.ListStatesUseCase
import com.example.sharebook.book_feature.domain.usecase.UpdateBookUseCase
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
object BookModule {

    @Provides
    @Singleton
    fun provideRequestBookService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): BookService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(BookService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(bookService: BookService): BookRepository {
        return BookRepositoryImpl(bookService)
    }

    @Provides
    @Singleton
    fun provideDetailsBookUseCase(bookRepository: BookRepository): DetailsBookUseCase {
        return DetailsBookUseCase(bookRepository)
    }

    @Provides
    @Singleton
    fun provideCreateBookUseCase(bookRepository: BookRepository): CreateBookUseCase {
        return CreateBookUseCase(bookRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateBookUseCase(bookRepository: BookRepository): UpdateBookUseCase {
        return UpdateBookUseCase(bookRepository)
    }

    @Provides
    @Singleton
    fun provideListStatesUseCase(bookRepository: BookRepository): ListStatesUseCase {
        return ListStatesUseCase(bookRepository)
    }
}