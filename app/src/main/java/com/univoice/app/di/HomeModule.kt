package com.univoice.app.di

import com.univoice.data.datasource.HomeDataSource
import com.univoice.data.repositoryimpl.HomeRepositoryImpl
import com.univoice.data_remote.api.HomeApiService
import com.univoice.data_remote.datasourceimpl.HomeDataSourceImpl
import com.univoice.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsHomeRepository(RepositoryImpl: HomeRepositoryImpl): HomeRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesHomeDataSource(DataSourceImpl: HomeDataSourceImpl): HomeDataSource
    }
}