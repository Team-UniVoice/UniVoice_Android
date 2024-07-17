package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.SettingDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseSettingDto
import com.univoice.data_remote.api.SettingApiService
import javax.inject.Inject

class SettingDataSourceImpl @Inject constructor(
    private val settingApiService: SettingApiService
) : SettingDataSource {
    override suspend fun getMyPage(): BaseResponse<ResponseSettingDto> =
        settingApiService.getMyPage()
}