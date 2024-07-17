package com.univoice.domain.repository

import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity

interface HomeRepository {
    suspend fun getNoticeQuickScan(): Result<List<QuickScanListEntity>?>

    suspend fun getNoticeAll(): Result<List<NoticeListEntity>?>
}