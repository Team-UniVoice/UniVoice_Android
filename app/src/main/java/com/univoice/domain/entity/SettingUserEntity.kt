package com.univoice.domain.entity

data class SettingUserEntity(
    val id: Int,
    val name: String,
    val collegeDepartment: String,
    val department: String,
    val admissionNumber: String,
    val university: String,
    val universityLogoImage: String,
)