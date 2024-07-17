package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.HomeDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeAllDto
import com.univoice.data.dto.response.ResponseNoticeQuickScanDto
import com.univoice.data_remote.api.HomeApiService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : HomeDataSource {
    override suspend fun getNoticeQuickScan(): BaseResponse<ResponseNoticeQuickScanDto> =
        homeApiService.getNoticeQuickScan()

    override suspend fun getNoticeAll(): BaseResponse<List<ResponseNoticeAllDto>> =
        homeApiService.getNoticeAll()
}