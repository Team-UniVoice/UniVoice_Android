package com.univoice.data.datasource

import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataSource {
    suspend fun saveUserId(userId: String, userPwd: String)
    fun getUserId(): Flow<String?>
    fun getUserPwd(): Flow<String?>
}