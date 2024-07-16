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
    private val USER_ACCESSTOKEN = stringPreferencesKey("USER_ACCESSTOKEN")

    override suspend fun saveUserAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[USER_ACCESSTOKEN] = accessToken
        }
    }

    override fun getUserAccessToken(): Flow<String?> = dataStore.data.map { preferences ->
        preferences[USER_ACCESSTOKEN]
    }

    override suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.remove(USER_ACCESSTOKEN)
        }
    }
}