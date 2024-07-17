package com.univoice.app.di

import com.univoice.data.datasource.NoticeDetailDataSource
import com.univoice.data.repositoryimpl.NoticeDetailRepositoryImpl
import com.univoice.data_remote.api.NoticeDetailApiService
import com.univoice.data_remote.datasourceimpl.NoticeDetailDataSourceImpl
import com.univoice.domain.repository.NoticeDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoticeDetailModule {
    @Provides
    @Singleton
    fun noticeDetailService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): NoticeDetailApiService = retrofit.create(NoticeDetailApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsNoticeDetailRepository(RepositoryImpl: NoticeDetailRepositoryImpl): NoticeDetailRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesNoticeDetailDataSource(DataSourceImpl: NoticeDetailDataSourceImpl): NoticeDetailDataSource
    }
}
