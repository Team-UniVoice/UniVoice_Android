package com.univoice.data_local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.univoice.data.datasource.UserPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSourceImpl @Inject constructor
    (
    private val dataStore: DataStore<Preferences>
) : UserPreferencesDataSource {
    private val USER_ID = stringPreferencesKey("USER_ID")
    private val USER_PWD = stringPreferencesKey("USER_PWD")

    override suspend fun saveUserId(userId: String, userPwd: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[USER_PWD] = userPwd
        }
    }

    override fun getUserId(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

    override fun getUserPwd(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_PWD]
    }
}