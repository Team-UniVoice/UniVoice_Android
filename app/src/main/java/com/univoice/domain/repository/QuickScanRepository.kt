package com.univoice.domain.repository

import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.domain.entity.HomeQuickScanListEntity
import com.univoice.domain.entity.QuickScanListEntity

interface QuickScanRepository {
    suspend fun postQuickScan(requestQuickScanDto: String): Result<List<QuickScanListEntity>?>
}