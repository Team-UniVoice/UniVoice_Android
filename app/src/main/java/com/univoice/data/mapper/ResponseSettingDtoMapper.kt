package com.univoice.data.mapper

import com.univoice.data.dto.response.ResponseSettingDto
import com.univoice.domain.entity.SettingUserEntity

fun ResponseSettingDto.toSettingUserEntity() = SettingUserEntity(
    id, name, collegeDepartment, department, admissionNumber, university, universityLogoImage,
)