package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseSettingDto

interface SettingDataSource {
    suspend fun getMyPage(): BaseResponse<ResponseSettingDto>
}