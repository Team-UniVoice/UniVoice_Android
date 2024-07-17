package com.univoice.app.di

import com.univoice.data.datasource.UniversityDataSource
import com.univoice.data.repositoryimpl.UniversityRepositoryImpl
import com.univoice.domain.repository.UniversityRepository
import com.univoice.data_remote.api.UniversityApiService
import com.univoice.data_remote.datasourceimpl.UniversityDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {
    @Provides
    @Singleton
    fun provideUniversityService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): UniversityApiService = retrofit.create(UniversityApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsUniversityRepository(repositoryImpl: UniversityRepositoryImpl): UniversityRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesUniversityDataSource(dataSourceImpl: UniversityDataSourceImpl): UniversityDataSource
    }
}
