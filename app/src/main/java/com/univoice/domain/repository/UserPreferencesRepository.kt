package com.univoice.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    suspend fun saveUserId(userId: String, userPwd: String)
    fun getUserId(): Flow<String?>
    fun getUserPwd(): Flow<String?>
}