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
    override suspend fun postQuickScan(requestQuickScanDto: String): Result<List<QuickScanListEntity>?> {
        return runCatching {
            quickScanDataSource.postQuickScan(RequestQuickScanDto(requestQuickScanDto)).data?.map { it.toQuickScanListEntity() }
        }
    }
}