package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.NoticeDetailDataSource
import com.univoice.data.mapper.toNoticeDetailEntity
import com.univoice.domain.entity.NoticeDetailEntity
import com.univoice.domain.repository.NoticeDetailRepository
import javax.inject.Inject

class NoticeDetailRepositoryImpl @Inject constructor(
    private val noticeDetailDataSource: NoticeDetailDataSource
) : NoticeDetailRepository {
    override suspend fun getNoticeDetail(noticeId: Int): Result<NoticeDetailEntity> {
        return runCatching {
            noticeDetailDataSource.getNoticeDetail(noticeId).data?.toNoticeDetailEntity() ?: NoticeDetailEntity(-1, "", "", 0, 0, "", "", "", "", "", -1, "", emptyList(), "", false, false, "")
        }
    }

    override suspend fun postNoticeLike(noticeId: Int): Result<Unit> {
        return runCatching {
            noticeDetailDataSource.postNoticeLike(noticeId)
        }
    }

    override suspend fun postNoticeDisLike(noticeId: Int): Result<Unit> {
        return runCatching {
            noticeDetailDataSource.postNoticeDelLike(noticeId)
        }
    }

    override suspend fun postNoticeDetailViewCount(noticeId: Int): Result<Unit> {
        return runCatching {
            noticeDetailDataSource.postNoticeDetailViewCount(noticeId).data ?: Unit
        }
    }
}