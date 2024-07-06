package com.univoice.di

import android.content.Context
import com.univoice.data.datasource.UserPreferencesDataSource
import com.univoice.data.repositoryimpl.UserPreferencesRepositoryImpl
import com.univoice.data_remote.datasourceimpl.UserPreferencesDataSourceImpl
import com.univoice.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesModule {

    @Provides
    @Singleton
    fun provideUserPreferencesDataSource(
        @ApplicationContext context: Context
    ): UserPreferencesDataSource = UserPreferencesDataSourceImpl(context)

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        dataSource: UserPreferencesDataSource
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(dataSource)
}
