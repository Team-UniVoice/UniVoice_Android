package com.univoice.domain.repository

import com.univoice.domain.entity.NoticeDetailEntity

interface NoticeDetailRepository {
    suspend fun getNoticeDetail(noticeId: Int): Result<NoticeDetailEntity>
}