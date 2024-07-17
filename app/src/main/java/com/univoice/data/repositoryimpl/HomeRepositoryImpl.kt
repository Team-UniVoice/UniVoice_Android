package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.HomeDataSource
import com.univoice.data.mapper.toNoticeListEntity
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.HomeQuickScanListEntity
import com.univoice.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getNoticeQuickScan(): Result<List<HomeQuickScanListEntity>?> {
        return runCatching {
            val result = homeDataSource.getNoticeQuickScan().data

            result?.let {
                listOf(
                    HomeQuickScanListEntity(
                        result.universityName,
                        result.universityNameCount,
                        result.universityLogoImage
                    ),
                    HomeQuickScanListEntity(
                        result.collegeDepartmentName,
                        result.collegeDepartmentCount,
                        result.collegeDepartmentLogoImage
                    ),
                    HomeQuickScanListEntity(
                        result.departmentName,
                        result.departmentCount,
                        result.departmentLogoImage
                    ),
                )
            }
        }
    }

    override suspend fun getNoticeAll(): Result<List<NoticeListEntity>?> {
        return runCatching {
            homeDataSource.getNoticeAll().data?.map { it.toNoticeListEntity() }
        }
    }
}