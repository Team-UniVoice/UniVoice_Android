package com.univoice.domain.repository

interface UniversityRepository {
    suspend fun postUniversityNames(): Result<List<String>>
}
