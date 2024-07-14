package com.univoice.domain.entity

data class NoticeCategoryListEntity(
    val name: String,
    val noticeData: List<NoticeListEntity>
)