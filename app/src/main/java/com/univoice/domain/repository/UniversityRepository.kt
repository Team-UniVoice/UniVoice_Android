package com.univoice.domain.repository

interface UniversityRepository {
    suspend fun getUniversityNames(): Result<List<String>>
}
