package com.univoice.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.univoice.data.datasource.UserPreferencesDataSource
import com.univoice.data.repositoryimpl.UserPreferencesRepositoryImpl
import com.univoice.data_local.UserPreferencesDataSourceImpl
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
    private const val PREFERENCE_NAME = "user_preferences"
    private val Context.dataStore by preferencesDataStore(name = PREFERENCE_NAME)

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideUserPreferencesDataSource(
        dataStore: DataStore<Preferences>
    ): UserPreferencesDataSource = UserPreferencesDataSourceImpl(dataStore)

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        dataSource: UserPreferencesDataSource
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(dataSource)
}
