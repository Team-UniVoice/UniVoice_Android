package com.univoice.app.di

import com.univoice.data.datasource.SettingDataSource
import com.univoice.data.repositoryimpl.SettingRepositoryImpl
import com.univoice.data_remote.api.SettingApiService
import com.univoice.data_remote.datasourceimpl.SettingDataSourceImpl
import com.univoice.domain.repository.SettingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingModule {
    @Provides
    @Singleton
    fun provideSettingService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): SettingApiService = retrofit.create(SettingApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsSettingRepository(RepositoryImpl: SettingRepositoryImpl): SettingRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesSettingDataSource(DataSourceImpl: SettingDataSourceImpl): SettingDataSource
    }
}