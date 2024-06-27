package com.univoice.di

import com.univoice.data.datasource.ExampleDataSource
import com.univoice.data.repositoryimpl.ExampleRepositoryImpl
import com.univoice.data_remote.api.ExampleApiService
import com.univoice.data_remote.datasourceimpl.ExampleDataSourceImpl
import com.univoice.domain.repository.ExampleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleModule {
    @Provides
    @Singleton
    fun provideSignService(@UniVoiceRetrofit retrofit: Retrofit): ExampleApiService =
        retrofit.create(ExampleApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsExampleRepository(
            RepositoryImpl: ExampleRepositoryImpl,
        ): ExampleRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesExampleDataSource(DataSourceImpl: ExampleDataSourceImpl): ExampleDataSource
    }
}