package com.univoice.domain.repository

import com.univoice.data.dto.response.ResponseUniversityDto

interface UniversityRepository {
    suspend fun getUniversityNames(): Result<ResponseUniversityDto>
}
