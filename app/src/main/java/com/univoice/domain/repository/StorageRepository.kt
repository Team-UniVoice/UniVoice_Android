package com.univoice.domain.repository

import com.univoice.domain.entity.NoticeListEntity

interface StorageRepository {
    suspend fun getSaves(): Result<List<NoticeListEntity>?>
}