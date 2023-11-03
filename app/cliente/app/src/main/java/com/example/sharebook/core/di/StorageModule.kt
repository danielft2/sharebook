package com.example.sharebook.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.sharebook.core.data.local.storage.TokenStorageManagerImpl
import com.example.sharebook.core.data.local.storage.UserStorageManagerImpl
import com.example.sharebook.core.domain.adapter.TokenStorageManagement
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.utils.dataStorage
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStorage
    }

    @Singleton
    @Provides
    fun provideUserStorageManagement(dataStore: DataStore<Preferences>, gson: Gson): UserStorageManagement {
        return UserStorageManagerImpl(dataStore, gson)
    }

    @Singleton
    @Provides
    fun provideTokenStorageManagement(dataStore: DataStore<Preferences>): TokenStorageManagement {
        return TokenStorageManagerImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}