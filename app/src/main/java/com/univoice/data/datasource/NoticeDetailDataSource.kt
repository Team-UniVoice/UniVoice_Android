package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto

interface NoticeDetailDataSource {
    suspend fun getNoticeDetail(noticeId: Int): BaseResponse<ResponseNoticeDetailDto>
    suspend fun postNoticeDetailViewCount(noticeId: Int): BaseResponse<Unit>
    suspend fun postNoticeDetailSave(noticeId: Int): BaseResponse<Unit>
    suspend fun postNoticeDetailCancel(noticeId: Int): BaseResponse<Unit>
}