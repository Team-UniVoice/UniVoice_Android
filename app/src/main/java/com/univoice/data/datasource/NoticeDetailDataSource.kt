package com.univoice.data.datasource

import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto

interface NoticeDetailDataSource {
    suspend fun getNoticeDetail(noticeId: Int): BaseResponse<ResponseNoticeDetailDto>

    suspend fun postNoticeLike(noticeId: Int): BaseResponse<Unit>

    suspend fun postNoticeDelLike(noticeId: Int): BaseResponse<Unit>
}