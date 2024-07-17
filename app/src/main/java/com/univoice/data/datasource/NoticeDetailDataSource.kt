package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto

interface NoticeDetailDataSource {
    suspend fun getNoticeDetail(noticeId: Int): BaseResponse<ResponseNoticeDetailDto>
}