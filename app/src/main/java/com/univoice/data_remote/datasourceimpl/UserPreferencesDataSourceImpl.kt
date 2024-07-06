package com.univoice.data_remote.datasourceimpl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.univoice.data.datasource.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesDataSourceImpl(private val context: Context) : UserPreferencesDataSource {
    private val USER_ID = stringPreferencesKey("USER_ID")
    private val USER_PWD = stringPreferencesKey("USER_PWD")

    override suspend fun saveUserId(userId: String, userPwd: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[USER_PWD] = userPwd
        }
    }

    override fun getUserId(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

    override fun getUserPwd(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[USER_PWD]
    }
}