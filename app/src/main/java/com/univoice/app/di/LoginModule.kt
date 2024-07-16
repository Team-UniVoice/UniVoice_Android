package com.univoice.app.di

import com.univoice.data.datasource.LoginDataSource
import com.univoice.data.repositoryimpl.LoginRepositoryImpl
import com.univoice.data.repositoryimpl.UserInfoRepositoryImpl
import com.univoice.data_remote.api.LoginApiService
import com.univoice.data_remote.datasourceimpl.LoginDataSourceImpl
import com.univoice.domain.repository.LoginRepository
import com.univoice.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    @Singleton
    fun provideLoginService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): LoginApiService = retrofit.create(LoginApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsLoginRepository(RepositoryImpl: LoginRepositoryImpl): LoginRepository

        @Binds
        @Singleton
        fun bindsUserInfoRepository(RepositoryImpl: UserInfoRepositoryImpl): UserInfoRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesLoginDataSource(DataSourceImpl: LoginDataSourceImpl): LoginDataSource
    }
}
