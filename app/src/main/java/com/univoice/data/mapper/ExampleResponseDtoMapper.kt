package com.univoice.data.mapper

import com.univoice.data.dto.response.Support
import com.univoice.data.dto.response.User
import com.univoice.data.dto.response.UserDataDto
import com.univoice.domain.entity.SupportEntity
import com.univoice.domain.entity.UserDataEntity
import com.univoice.domain.entity.UserEntity

fun User.toUserEntity() = UserEntity(
    id, email, firstName, lastName, avatar
)

fun UserDataDto.toUserDataEntity() = UserDataEntity(
    page, perPage, total, totalPages, data.map { it.toUserEntity() }, support.toSupportEntity()
)

fun Support.toSupportEntity() = SupportEntity(
    url, text
)