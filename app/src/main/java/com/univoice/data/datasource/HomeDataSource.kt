package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeAllDto
import com.univoice.data.dto.response.ResponseNoticeQuickScanDto

interface HomeDataSource {
    suspend fun getNoticeQuickScan(): BaseResponse<ResponseNoticeQuickScanDto>

    suspend fun getNoticeAll(): BaseResponse<List<ResponseNoticeAllDto>>
}