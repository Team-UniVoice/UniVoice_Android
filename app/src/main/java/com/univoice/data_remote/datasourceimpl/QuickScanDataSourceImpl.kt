package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.QuickScanDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.data.dto.response.ResponseQuickScanDto
import com.univoice.data_remote.api.QuickScanApiService
import javax.inject.Inject

class QuickScanDataSourceImpl @Inject constructor(
    private val quickScanApiService: QuickScanApiService
) : QuickScanDataSource {
    override suspend fun postQuickScan(requestQuickScanDto: RequestQuickScanDto): BaseResponse<List<ResponseQuickScanDto>> {
        return quickScanApiService.postQuickScan(requestQuickScanDto)
    }
}