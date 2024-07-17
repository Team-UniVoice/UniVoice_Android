package com.univoice.domain.repository

import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.HomeQuickScanListEntity

interface HomeRepository {
    suspend fun getNoticeQuickScan(): Result<List<HomeQuickScanListEntity>?>

    suspend fun getNoticeAll(): Result<List<NoticeListEntity>?>

    suspend fun getNoticeUniversity(): Result<List<NoticeListEntity>?>

    suspend fun getNoticeCollege(): Result<List<NoticeListEntity>?>

    suspend fun getNoticeDepartment(): Result<List<NoticeListEntity>?>
}