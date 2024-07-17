package com.univoice.domain.repository

import com.univoice.domain.entity.QuickScanListEntity

interface QuickScanRepository {
    suspend fun postQuickScan(writeAffiliation: String): Result<List<QuickScanListEntity>?>
}