package com.univoice.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    suspend fun saveUserAccessToken(accessToken: String)
    fun getUserAccessToken(): Flow<String?>

    suspend fun clear()
}