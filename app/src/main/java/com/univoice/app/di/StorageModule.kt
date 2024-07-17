package com.univoice.app.di

import com.univoice.data.datasource.StorageDataSource
import com.univoice.data.repositoryimpl.StorageRepositoryImpl
import com.univoice.data_remote.api.StorageApiService
import com.univoice.data_remote.datasourceimpl.StorageDataSourceImpl
import com.univoice.domain.repository.StorageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Provides
    @Singleton
    fun provideStorageService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): StorageApiService = retrofit.create(StorageApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsStorageRepository(RepositoryImpl: StorageRepositoryImpl): StorageRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesStorageDataSource(DataSourceImpl: StorageDataSourceImpl): StorageDataSource
    }
}