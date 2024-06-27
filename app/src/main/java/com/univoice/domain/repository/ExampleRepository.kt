package com.univoice.domain.repository

import com.univoice.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    suspend fun getExample(page: Int): Flow<List<UserEntity>>
}