package com.univoice.domain.repository

import com.univoice.domain.entity.StorageListEntity

interface StorageRepository {
    suspend fun getSaves(): Result<List<StorageListEntity>?>
}