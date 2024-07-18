package com.univoice.data_remote.datasourceimpl

import com.univoice.data.datasource.NoticeDetailDataSource
import com.univoice.data.dto.BaseResponse
import com.univoice.data.dto.response.ResponseNoticeDetailDto
import com.univoice.data_remote.api.NoticeDetailApiService
import javax.inject.Inject

class NoticeDetailDataSourceImpl @Inject constructor(
    private val noticeDetailApiService: NoticeDetailApiService
) : NoticeDetailDataSource {
    override suspend fun getNoticeDetail(noticeId: Int): BaseResponse<ResponseNoticeDetailDto> {
        return noticeDetailApiService.getNoticeDetail(noticeId)
    }

    override suspend fun postNoticeLike(noticeId: Int): BaseResponse<Unit> {
        return noticeDetailApiService.postNoticeLike(noticeId)
    }

    override suspend fun postNoticeCancelLike(noticeId: Int): BaseResponse<Unit> {
        return noticeDetailApiService.postNoticeCancelLike(noticeId)
    }

    override suspend fun postNoticeDetailViewCount(noticeId: Int): BaseResponse<Unit> {
        return noticeDetailApiService.postNoticeDetailViewCount(noticeId)
    }

    override suspend fun postNoticeDetailSave(noticeId: Int): BaseResponse<Unit> {
        return noticeDetailApiService.postNoticeDetailSave(noticeId)
    }

    override suspend fun postNoticeDetailCancel(noticeId: Int): BaseResponse<Unit> {
        return noticeDetailApiService.postNoticeDetailCancel(noticeId)
    }
}