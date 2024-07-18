package com.univoice.domain.repository

import com.univoice.domain.entity.SettingUserEntity

interface SettingRepository {
    suspend fun getMyPage(): Result<SettingUserEntity>
}