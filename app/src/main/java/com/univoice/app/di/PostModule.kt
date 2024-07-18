package com.univoice.app.di

import com.univoice.data.datasource.PostDataSource
import com.univoice.data.repositoryimpl.PostRepositoryImpl
import com.univoice.data_remote.api.PostApiService
import com.univoice.data_remote.datasourceimpl.PostDataSourceImpl
import com.univoice.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {
    @Provides
    @Singleton
    fun providePostService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): PostApiService = retrofit.create(PostApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsPostRepository(repositoryImpl: PostRepositoryImpl): PostRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Binds
        @Singleton
        fun providesPostDataSource(dataSourceImpl: PostDataSourceImpl): PostDataSource
    }
}