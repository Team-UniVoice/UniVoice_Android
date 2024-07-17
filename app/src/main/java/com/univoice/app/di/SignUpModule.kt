package com.univoice.app.di

import com.univoice.data.datasource.SignUpDataSource
import com.univoice.data.repositoryimpl.SignUpRepositoryImpl
import com.univoice.domain.repository.SignUpRepository
import com.univoice.data_remote.api.SignUpApiService
import com.univoice.data_remote.datasourceimpl.SignUpDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SignUpModule {

    @Provides
    @Singleton
    fun provideSignUpService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): SignUpApiService = retrofit.create(SignUpApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsSignUpRepository(repositoryImpl: SignUpRepositoryImpl): SignUpRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Binds
        @Singleton
        fun providesSignUpDataSource(dataSourceImpl: SignUpDataSourceImpl): SignUpDataSource
    }
}
