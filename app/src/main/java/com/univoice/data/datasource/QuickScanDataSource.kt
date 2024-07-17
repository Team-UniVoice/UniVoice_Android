package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.request.RequestQuickScanDto
import com.univoice.data.dto.response.ResponseQuickScanDto

interface QuickScanDataSource {
    suspend fun postQuickScan(requestQuickScanDto: RequestQuickScanDto): BaseResponse<List<ResponseQuickScanDto>>
    suspend fun postQuickScanViewCheck(noticeId: Int): BaseResponse<Unit>
}