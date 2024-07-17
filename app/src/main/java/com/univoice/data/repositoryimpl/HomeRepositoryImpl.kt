package com.univoice.data.repositoryimpl

import com.univoice.data.datasource.HomeDataSource
import com.univoice.data.mapper.toNoticeListEntity
import com.univoice.domain.entity.NoticeListEntity
import com.univoice.domain.entity.QuickScanListEntity
import com.univoice.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getNoticeQuickScan(): Result<List<QuickScanListEntity>?> {
        return runCatching {
            val result = homeDataSource.getNoticeQuickScan().data

            result?.let {
                listOf(
                    QuickScanListEntity(
                        result.universityName,
                        result.universityNameCount,
                        result.universityLogoImage
                    ),
                    QuickScanListEntity(
                        result.collegeDepartmentName,
                        result.collegeDepartmentCount,
                        result.collegeDepartmentLogoImage
                    ),
                    QuickScanListEntity(
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

    override suspend fun getNoticeUniversity(): Result<List<NoticeListEntity>?> {
        return runCatching {
            homeDataSource.getNoticeUniversity().data?.map { it.toNoticeListEntity() }
        }
    }

    override suspend fun getNoticeCollege(): Result<List<NoticeListEntity>?> {
        return runCatching {
            homeDataSource.getNoticeCollege().data?.map { it.toNoticeListEntity() }
        }
    }

    override suspend fun getNoticeDepartment(): Result<List<NoticeListEntity>?> {
        return runCatching {
            homeDataSource.getNoticeDepartment().data?.map { it.toNoticeListEntity() }
        }
    }
}