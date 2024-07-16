package com.univoice.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    suspend fun saveUserAccessToken(accessToken: String)

    fun getUserAccessToken(): Flow<String?>
}