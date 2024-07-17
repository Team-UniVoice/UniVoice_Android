package com.univoice.domain.repository

interface LoginRepository {
    suspend fun postLogin(email: String, password: String): Result<String?>
}