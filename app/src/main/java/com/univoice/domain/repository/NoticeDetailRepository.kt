package com.univoice.domain.repository

import com.univoice.domain.entity.NoticeDetailEntity

interface NoticeDetailRepository {
    suspend fun getNoticeDetail(noticeId: Int): Result<NoticeDetailEntity>

    suspend fun postNoticeLike(noticeId: Int): Result<Unit>

    suspend fun postNoticeCancelLike(noticeId: Int): Result<Unit>
  
    suspend fun postNoticeDetailViewCount(noticeId: Int): Result<Unit>

    suspend fun postNoticeDetailSave(noticeId: Int): Result<Unit>

    suspend fun postNoticeDetailCancel(noticeId: Int): Result<Unit>
}