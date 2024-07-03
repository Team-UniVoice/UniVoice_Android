package com.univoice.domain.repository

import com.univoice.domain.entity.UserEntity

interface ExampleRepository {
    suspend fun getExample(page: Int): Result<List<UserEntity>>
}