package com.univoice.app.di

import com.univoice.data.datasource.QuickScanDataSource
import com.univoice.data.repositoryimpl.QuickScanRepositoryImpl
import com.univoice.data_remote.api.QuickScanApiService
import com.univoice.data_remote.datasourceimpl.QuickScanDataSourceImpl
import com.univoice.domain.repository.QuickScanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuickScanModule {
    @Provides
    @Singleton
    fun provideQuickScanService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): QuickScanApiService = retrofit.create(QuickScanApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsQuickScanRepository(RepositoryImpl: QuickScanRepositoryImpl): QuickScanRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesQuickScanDataSource(DataSourceImpl: QuickScanDataSourceImpl): QuickScanDataSource
    }
}