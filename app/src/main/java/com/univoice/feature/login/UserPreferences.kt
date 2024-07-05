package com.univoice.feature.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private val Context.dataStore by preferencesDataStore(name = "user_preferences")
object UserPreferences {
    private val USER_ID = stringPreferencesKey("USER_ID")
    private val USER_PWD = stringPreferencesKey("USER_PWD")

    suspend fun saveUserId(context: Context, userId: String, userPwd: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
            preferences[USER_PWD] = userPwd
        }
    }
}