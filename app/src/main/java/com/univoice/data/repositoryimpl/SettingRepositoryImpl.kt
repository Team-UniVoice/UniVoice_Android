package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.SettingDataSource
import com.univoice.data.mapper.toSettingUserEntity
import com.univoice.domain.entity.SettingUserEntity
import com.univoice.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val settingDataSource: SettingDataSource
) : SettingRepository {
    override suspend fun getMyPage(): Result<SettingUserEntity> {
        return runCatching {
            settingDataSource.getMyPage().data?.toSettingUserEntity() ?: SettingUserEntity(
                -1,
                "",
                "",
                "",
                "",
                "",
                "",
            )
        }
    }
}