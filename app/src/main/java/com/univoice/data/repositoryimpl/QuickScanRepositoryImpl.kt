package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.QuickScanDataSource
import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.data.mapper.toQuickScanListEntity
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.domain.repository.QuickScanRepository
import javax.inject.Inject

class QuickScanRepositoryImpl @Inject constructor(
    private val quickScanDataSource: QuickScanDataSource
) : QuickScanRepository {
    override suspend fun postQuickScan(writeAffiliation: String): Result<List<QuickScanListEntity>?> {
        return runCatching {
            quickScanDataSource.postQuickScan(RequestQuickScanDto(writeAffiliation)).data?.map { it.toQuickScanListEntity() }
        }
    }

    override suspend fun postQuickScanViewCheck(noticeId: Int): Result<Unit> {
        return runCatching {
            quickScanDataSource.postQuickScanViewCheck(noticeId)
        }
    }
}