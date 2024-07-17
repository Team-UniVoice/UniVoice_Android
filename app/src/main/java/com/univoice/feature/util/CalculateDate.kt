package com.univoice.feature.util

import android.content.Context
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalculateDate() {
    fun getCalculateDate(dateTimeString: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val targetDate = LocalDate.parse(dateTimeString.substring(0, 10))
        return targetDate.format(dateFormat)
    }
}