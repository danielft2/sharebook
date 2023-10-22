package com.example.sharebook.core.di

import com.example.sharebook.core.data.remote.respository.CepRepositoryImpl
import com.example.sharebook.core.data.remote.service.CepService
import com.example.sharebook.core.domain.adapter.CepRepository
import com.example.sharebook.core.domain.usecase.ConsultCepUseCase
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
class CepModule {

    @Provides
    @Singleton
    fun provideCepService(): CepService {
        return Retrofit.Builder()
            .baseUrl(Constants.VIA_CEP_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CepService::class.java)
    }

    @Provides
    @Singleton
    fun provideCepRepository(cepService: CepService): CepRepository {
        return CepRepositoryImpl(cepService)
    }

    @Provides
    @Singleton
    fun provideConsultCepUseCase(cepRepository: CepRepository): ConsultCepUseCase {
        return ConsultCepUseCase(cepRepository);
    }
}