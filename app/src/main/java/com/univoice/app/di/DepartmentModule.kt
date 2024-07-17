package com.univoice.app.di

import com.univoice.data.datasource.DepartmentDataSource
import com.univoice.data.repositoryimpl.DepartmentRepositoryImpl
import com.univoice.domain.repository.DepartmentRepository
import com.univoice.data_remote.api.DepartmentApiService
import com.univoice.data_remote.datasourceimpl.DepartmentDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DepartmentModule {
    @Provides
    @Singleton
    fun provideDepartmentService(
        @UniVoiceRetrofit retrofit: Retrofit,
    ): DepartmentApiService = retrofit.create(DepartmentApiService::class.java)

    @Module
    @InstallIn(SingletonComponent::class)
    interface RepositoryModule {
        @Binds
        @Singleton
        fun bindsDepartmentRepository(repositoryImpl: DepartmentRepositoryImpl): DepartmentRepository
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface DataSourceModule {
        @Singleton
        @Binds
        fun providesDepartmentDataSource(dataSourceImpl: DepartmentDataSourceImpl): DepartmentDataSource
    }
}
